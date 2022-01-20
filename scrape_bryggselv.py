import requests
from bs4 import BeautifulSoup
import re
import time
import string
from flask import Flask, jsonify
import threading
import concurrent.futures

app = Flask(__name__)

class Beer():
    def __init__(self, name, recipe_link, picture, og, ibu, abv, fg, ebc):
        self.name = name
        self.recipe_link = recipe_link
        self.picture = picture
        self.og = og
        self.ibu = ibu
        self.abv = abv
        self.fg = fg
        self.ebc = ebc

    def print_beer(self):
        print("name: " + str(self.name))
        print("recipe_link: " + str(self.recipe_link))
        print("picture: " + str(self.picture))
        print("og: " + str(self.og))
        print("ibu: " + str(self.ibu))
        print("abv: " + str(self.abv))
        print("fg: " + str(self.fg))
        print("ebc: " + str(self.ebc))


def get_links():
    """
    Fetches data from bracket, ectracts team-name and url for teams that appeared in
    the conference semifinals
    Args:
        URL (string): url of 2020 NBA
    Returns:
        array: 2d list with pairs of team names and URL for wikipedia entry of team
    """
    temp = requests.get("https://www.bryggselv.no/%C3%B8lsett/allgrain")
    html = temp.text
    baseurl = "https://www.bryggselv.no"
    document = BeautifulSoup(html, 'html.parser')
    list = document.find_all("div", {"class":"AddProductImage"})
    liste = []
    for element in list:
        links = element.find_all("a")
        if links != []:
            links = links[0].get("href")
        liste.append(baseurl+links)

    return liste

def get_data(links):
    beer_list = []
    for l in links:
        temp = requests.get(l)
        html = temp.text
        baseurl = "https://www.bryggselv.no"
        document = BeautifulSoup(html, 'html.parser')

        bilde = document.find_all("img", {"class": "rsImg"})
        link = bilde[0].get("src")
        link = str(link)
        link = baseurl + link

        div = document.find_all("div", {"class":"heading-container"})
        tittel = div[0].find("h1")
        tittel = str(tittel)
        tittel = re.sub("<.+?>", "", tittel)


        div = document.find_all("div", {"class": "prod-text-content"})
        a = div[0].find("a")
        oppskrift = a.get("href")
        oppskrift = str(oppskrift)
        info = div[0].find("strong")
        info = re.sub("~|%|\+", "", str(info))
        info = re.sub(",", ".", info)
        infolist = str(info).split()

        og = infolist[1].split("-")
        og = avg_value_og_fg(og, float) if len(og) == 2 else float(og[0])

        ibu = infolist[4].split("-")
        ibu = avg_value_abv_ibu_ebc(ibu, int) if len(ibu) == 2 else int(ibu[0])

        abv = infolist[7].split("-")
        abv = avg_value_abv_ibu_ebc(abv, float) if len(abv) == 2 else float(abv[0])

        fg = infolist[10].split("-")
        fg = avg_value_og_fg(fg, float) if len(fg) == 2 else float(fg[0])

        ebc = infolist[12].split("-")
        ebc = avg_value_abv_ibu_ebc(ebc, float) if len(ebc) == 2 else float(infolist[12])
        #print(infolist)



        beer_list.append(Beer(tittel, oppskrift, link, og, ibu, abv, fg, ebc))

    return beer_list

def get_data1(url):

    temp = requests.get(url)
    html = temp.text
    baseurl = "https://www.bryggselv.no"
    document = BeautifulSoup(html, 'html.parser')

    bilde = document.find_all("img", {"class": "rsImg"})
    link = bilde[0].get("src")
    link = str(link)
    link = baseurl + link

    div = document.find_all("div", {"class":"heading-container"})
    tittel = div[0].find("h1")
    tittel = str(tittel)
    tittel = re.sub("<.+?>", "", tittel)


    div = document.find_all("div", {"class": "prod-text-content"})
    a = div[0].find("a")
    oppskrift = a.get("href")
    oppskrift = str(oppskrift)
    info = div[0].find("strong")
    info = re.sub("~|%|\+", "", str(info))
    info = re.sub(",", ".", info)
    infolist = str(info).split()

    og = infolist[1].split("-")
    og = avg_value_og_fg(og, float) if len(og) == 2 else float(og[0])

    ibu = infolist[4].split("-")
    ibu = avg_value_abv_ibu_ebc(ibu, int) if len(ibu) == 2 else int(ibu[0])

    abv = infolist[7].split("-")
    abv = avg_value_abv_ibu_ebc(abv, float) if len(abv) == 2 else float(abv[0])

    fg = infolist[10].split("-")
    fg = avg_value_og_fg(fg, float) if len(fg) == 2 else float(fg[0])

    ebc = infolist[12].split("-")
    ebc = avg_value_abv_ibu_ebc(ebc, float) if len(ebc) == 2 else float(infolist[12])
    #print(infolist)

    print("inserting beer " + tittel)
    return beer_list.append(" i")

#experimental - not working
def scrape_objects(URLS):
    beer_list=[]
    for url in URLS[:-1]:
  #folderName = url.split('/')[-1] # the name of the folder
        # with concurrent.futures.ThreadPoolExecutor() as executor:
        #     future = executor.submit(get_data1, url)
        #     beer_list.append(future.result())
        processThread = threading.Thread(
            target=get_data1, args=(url)) # parameters and functions have to be passed separately
        processThread.start() # start the thread

def avg_value_og_fg(list, type):
    baseval = type(list[0])
    highval = type("1.0" + list[1])
    diff = highval-baseval
    return baseval + (diff/2)

def avg_value_abv_ibu_ebc(list, type):
    baseval = type(list[0])
    highval = type(list[1])
    diff = highval-baseval
    return baseval + (diff/2)

def create_json(beer_list):
    json_objects = []
    for beer in beer_list:
        json_objects.append({
            "name" : beer.name,
            "recipeLink" : beer.recipe_link,
            "pictureLink" : beer.picture,
            "og" : beer.og,
            "ibu" : beer.ibu,
            "abv" : beer.abv,
            "fg" : beer.fg,
            "ebc" : beer.ebc
        })
    return json_objects

@app.route('/api/beers', methods=['GET'])
def get_beers():
    return jsonify(json_objects)

if __name__ == "__main__":
    links = get_links()
    #beer_list = scrape_objects(links)
    #print(beer_list)
    #last link does not contain beer info
    beer_list = get_data(links[:-1])
    json_objects = create_json(beer_list)

    app.run(debug=True)
