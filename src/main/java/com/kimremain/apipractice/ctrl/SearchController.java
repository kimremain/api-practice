package com.kimremain.apipractice.ctrl;

import com.google.gson.Gson;
import com.kimremain.apipractice.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class SearchController extends BaseController{
    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/search")
    public ModelAndView searchHtml(@RequestParam(value = "location", defaultValue = "서울") String location,
                               @RequestParam(value = "keyword", defaultValue = "중식당") String keyword) throws Exception {
        logger.debug("call SearchController searchHtml...");
        logger.debug("location:"+location);
        logger.debug("keyword:"+keyword);

        ModelAndView view = new ModelAndView("main");
        searchService = new SearchService();
        view.addObject("location", location);
        view.addObject("keyword", keyword);
        view.addObject("posts", searchService.getData(location,keyword));

        //model.addAttribute("posts", searchService.getData("서울","중식당"));
        return view;
    }

    @GetMapping(value = "/search", produces="application/json")
    public String searchJson(@RequestParam(value = "location", defaultValue = "서울") String location,
                         @RequestParam(value = "keyword", defaultValue = "중식당") String keyword) throws Exception {
        logger.debug("call SearchController searchJson...");
        logger.debug("location:"+location);
        logger.debug("keyword:"+keyword);

        searchService = new SearchService();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(searchService.getData(location,keyword));

        return jsonArray;
    }

    @GetMapping(value = "/searchMerge")
    public ModelAndView searchMerge(@RequestParam(value = "location", defaultValue = "서울") String location,
                                   @RequestParam(value = "keyword", defaultValue = "중식당") String keyword) throws Exception {

        return null;
    }
}
