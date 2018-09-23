package com.kimremain.apipractice.ctrl;

import com.kimremain.apipractice.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class SearchController extends BaseController{

    private SearchService searchService;


    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "location", defaultValue = "서울") String location,
                               @RequestParam(value = "keyword", defaultValue = "중식당") String keyword) throws Exception {
        logger.debug("call SearchController main...");
        searchService = new SearchService();
        ModelAndView view = new ModelAndView("main");
        logger.debug("location:"+location);
        logger.debug("keyword:"+keyword);
        view.addObject("location", location);
        view.addObject("keyword", keyword);
        view.addObject("posts", searchService.getData(location,keyword));

        //model.addAttribute("posts", searchService.getData("서울","중식당"));
        return view;
    }
}
