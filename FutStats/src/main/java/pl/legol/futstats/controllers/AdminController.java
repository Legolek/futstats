package pl.legol.futstats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.legol.futstats.providers.impl.DataLoaderService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DataLoaderService dataLoaderService;

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String forceLoad() {
        dataLoaderService.retrieveData();
        return "OK";
    }

}
