package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class CountryController {
    //    Surf to localhost:2018/data/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountriesApplication.ourCountryList.countryList.sort((c1,c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList, HttpStatus.OK);
    }
    //    Surf to localhost:2018/data/names/start/{letter}
//    return the countries alphabetically that begin with the given letter
    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountryByName(@PathVariable char letter)
    {
        ArrayList<Country> returnCountries =
                CountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));

        returnCountries.sort((c1,c2) -> c1.getName().compareToIgnoreCase((c2.getName())));

        return new ResponseEntity<>(returnCountries, HttpStatus.OK);
    }

//    Surf to localhost:2018/data/names/size/{number}
//    return the countries alphabetically that have a name equal to or longer than the given length
    @GetMapping(value = "/names/size/{number}", produces = {"application/json"})
    public ResponseEntity<?> getCountryNameLongerThan(@PathVariable int number)
    {
        ArrayList<Country> returnCountries =
                CountriesApplication.ourCountryList.findCountries(c -> c.getName().length() >= number);

        returnCountries.sort((c1,c2) -> c1.getName().compareToIgnoreCase((c2.getName())));

        return new ResponseEntity<>(returnCountries, HttpStatus.OK);
    }

//    Surf to localhost:2018/data/population/size/{people}
//    return the countries that have a population equal to or greater than the given population
    @GetMapping(value = "/population/size/{people}", produces = {"application/json"})
    public  ResponseEntity<?> getCountryPopLargerThan(@PathVariable long people)
    {
        ArrayList<Country> returnCountries =
                CountriesApplication.ourCountryList.findCountries( c -> c.getPopulation() >= people);

        returnCountries.sort((c1,c2) -> (int) (c1.getPopulation() - c2.getPopulation()));

        return new ResponseEntity<>(returnCountries, HttpStatus.OK);
    }

//    Surf to localhost:2018/data/population/min
//    return the country with the smallest population
    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getCountrySmallestPop()
    {
       CountriesApplication.ourCountryList.countryList.sort((c1,c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        return new ResponseEntity<>((CountriesApplication.ourCountryList.countryList.get(0)), HttpStatus.OK);
    }

//    Surf to localhost:2018/data/population/max
//    return the country with the largest population

    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getCountryLargestPop()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1,c2) -> (int)(c2.getPopulation() - c1.getPopulation()));

        return new ResponseEntity<>((CountriesApplication.ourCountryList.countryList.get(0)), HttpStatus.OK);
    }

//    Surf to localhost:2018/data/age/age/{age}
//    return the country with the largest population
    @GetMapping(value = "/age/age/{age}", produces = {"application/json"})
    public ResponseEntity<?> getMedianLargerThan(@PathVariable long age)
    {
        ArrayList<Country> returnCountry = CountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age);

        return new ResponseEntity<>(returnCountry, HttpStatus.OK);
    }

    //    Surf to localhost:2018/data/age/min
//    return the country with the smallest median age
    @GetMapping(value = "/age/min", produces = {"application/json"})
    public ResponseEntity<?> getMedianMin()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1,c2) -> (int)(c1.getMedianAge() - c2.getMedianAge()));

        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

//    Surf to localhost:2018/data/age/max
//    return the country with the largest median age
    @GetMapping(value = "/age/max", produces = {"application/json"})
    public ResponseEntity<?> getMedianMax()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1,c2) -> (int)(c2.getMedianAge() - c1.getMedianAge()));

        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

}






