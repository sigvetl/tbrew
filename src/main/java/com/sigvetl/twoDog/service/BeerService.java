package com.sigvetl.twoDog.service;

import com.sigvetl.twoDog.mapper.BeerMapper;
import com.sigvetl.twoDog.model.Beer;
import com.sigvetl.twoDog.model.BeerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerMapper beerMapper;

    @Autowired
    private UserService userService;

    private Integer userId = null;

    public void createFromApi(Beer beer){
        beer.setUserId(this.userId);
        beer.setCreatedBy(userService.getUserFullname(this.userId));
        beer.setLastUpdatedBy(userService.getUserFullname(this.userId));
        beer.setUserCreated(Boolean.FALSE);
        this.beerMapper.insertBeer(beer);
    }

    public void updateFromApi(Beer beer){
        beer.setUserId(this.userId);
        beer.setCreatedBy(userService.getUserFullname(this.userId));
        beer.setLastUpdatedBy(userService.getUserFullname(this.userId));
        beer.setUserCreated(Boolean.FALSE);
        this.beerMapper.updateBeer(beer);
    }

    public void createBeer(BeerForm beerForm){
        Beer beer = new Beer();
        beer.setIbu(beerForm.getIbu());
        beer.setName(beerForm.getName());
        beer.setType(beerForm.getType());
        beer.setRecipeLink(beerForm.getRecipeLink());
        beer.setUserId(this.userId);
        beer.setCreatedBy(userService.getUserFullname(this.userId));
        beer.setLastUpdatedBy(userService.getUserFullname(this.userId));
        beer.setOg(beerForm.getOg());
        beer.setFg(beerForm.getFg());
        beer.setPictureLink(beerForm.getPictureLink());
        beer.setAbv(beerForm.getAbv());
        beer.setEbc(beerForm.getEbc());
        beer.setUserCreated(Boolean.TRUE);
        this.beerMapper.insertBeer(beer);
    }

    public void updateBeer(BeerForm beerForm){
        Beer beer = new Beer();
        beer.setBeerId(beerForm.getBeerId());
        beer.setIbu(beerForm.getIbu());
        beer.setName(beerForm.getName());
        beer.setType(beerForm.getType());
        beer.setRecipeLink(beerForm.getRecipeLink());
        beer.setUserId(this.userId);
        beer.setLastUpdatedBy(userService.getUserFullname(this.userId));
        beer.setOg(beerForm.getOg());
        beer.setFg(beerForm.getFg());
        beer.setPictureLink(beerForm.getPictureLink());
        beer.setAbv(beerForm.getAbv());
        beer.setEbc(beerForm.getEbc());
        beer.setUserCreated(Boolean.TRUE);
        this.beerMapper.updateBeer(beer);
    }

    public void deleteBeer(Integer beerId){
        this.beerMapper.deleteBeer(beerId);
    }

    public List<Beer> getBeers(){
        return this.beerMapper.getAllBeers();
    }

    public boolean beerExists(BeerForm beerForm){
        Beer beer = new Beer();
        beer.setBeerId(beerForm.getBeerId());
        boolean exists = this.beerMapper.getBeer(beer) != null;
        return exists;
    }

    public int beerNameExists(Beer beer){
        Beer returnedBeer = this.beerMapper.getBeerByName(beer);
        if (returnedBeer != null){
            return returnedBeer.getBeerId();
        } else{
            return 0;
        }
    }

    public void trackLoggedInUserId(String username){
        this.userId = userService.getUser(username).getUserId();
    }

    public String getBeerNameById(Integer beerId){
        return beerMapper.getBeerById(beerId).getName();
    }
}
