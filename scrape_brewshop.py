import requests
from bs4 import BeautifulSoup
import re
import time
import string
from flask import Flask, jsonify
import threading

app = Flask(__name__)

class Beer():
    def __init__(self, name, recipe_link, picture, og, fg, ebc, ibu, abv):
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
    temp = requests.get("https://brewshop.no/butikk/ravarer/allgrain-olsett/project-brew-olsett")
    html = temp.text
    baseurl = "https://brewshop.no"
    document = BeautifulSoup(html, 'html.parser')
    list = document.find_all("article", {"class":"productlist__product equal-height-column"})
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
        baseurl = "https://brewshop.no"
        document = BeautifulSoup(html, 'html.parser')

        bilde = document.find_all("a", {"class":"fancybox"})
        bildelink = bilde[0].find_all("img")
        link = bildelink[0].get("src")
        link = str(link)
        bildelink = baseurl + link

        div = document.find_all("div", {"class":"product__info"})
        tittel = div[0].find("h1")
        tittel = str(tittel)
        tittel = tittel.split("-")
        tittel = re.sub("<.+?>", "", tittel[0])
        tittel = re.sub("Ã¸", "ø", tittel)
        tittel = re.sub("Ã¶", "ö", tittel)
        


        div = document.find_all("div", {"class":"product__about"})
        
        oppskrift = "N/A"
        infolist = div[0].find_all("ul")
        info = infolist[1].find_all("li")
        beer = sort_data(info[2:8], tittel, bildelink)
        beer_list.append(beer)
        
    print(beer_list)
    for beer in beer_list:
        print(beer.name)
        print(beer.recipe_link)
        print(beer.picture)
        print(beer.og)
        print(beer.fg)
        print(beer.ebc)
        print(beer.ibu)
        print(beer.abv)

        # og = infolist[1].split("-")
        # og = avg_value_og_fg(og, float) if len(og) == 2 else float(og[0])

        # ibu = infolist[4].split("-")
        # ibu = avg_value_abv_ibu_ebc(ibu, int) if len(ibu) == 2 else int(ibu[0])

        # abv = infolist[7].split("-")
        # abv = avg_value_abv_ibu_ebc(abv, float) if len(abv) == 2 else float(abv[0])

        # fg = infolist[10].split("-")
        # fg = avg_value_og_fg(fg, float) if len(fg) == 2 else float(fg[0])

        # ebc = infolist[12].split("-")
        # ebc = avg_value_abv_ibu_ebc(ebc, float) if len(ebc) == 2 else 0 if ebc[0] == "</strong>" else float(infolist[12])
        # #print(infolist)



        #beer_list.append(Beer(tittel, oppskrift, link, og, ibu, abv, fg, ebc))

    return beer_list

def sort_data(list, tittel, bildelink):
    infolist = []
    for element in list:
        infos = re.sub("~|%|\+", "", str(element))
        infos = re.sub(",", ".", infos)
        infos = re.sub("</span></li>", "", infos)
        templist = str(infos).split()
        #print(templist)
        infolist.append(templist[3])
    print(infolist)
    #Adjust for one element with extra line
    if infolist[0] == "ca":
        infolist[2] = avg_value_og_fg(infolist[2].split("-"), float)
        return Beer(tittel, "", bildelink, float(infolist[1]), float(infolist[2]), float(infolist[3]), float(infolist[4]), float(infolist[5]))
    return Beer(tittel, "", bildelink, float(infolist[0]), float(infolist[1]), float(infolist[2]), float(infolist[3]), float(infolist[4]))
   # return infolist


def avg_value_og_fg(list, type):
    baseval = type(list[0])
    #error in value, this is a hack
    highval = type(1.02)
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
    for l in links:
        print(l)
    beer_list = get_data(links)
    json_objects = create_json(beer_list)

    app.run(debug=True)