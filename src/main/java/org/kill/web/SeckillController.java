package org.kill.web;

import org.kill.dto.Exposer4fun;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    
    @RequestMapping(name="/list",method = RequestMethod.GET)
    public String list(Model model){
        
        return null;
    }
}
