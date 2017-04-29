package org.kill.web;

import java.util.Date;
import java.util.List;

import org.kill.dto.Exposer;
import org.kill.dto.SeckillExecution;
import org.kill.dto.SeckillResult;
import org.kill.entity.Seckill;
import org.kill.exception.RepeatException;
import org.kill.exception.SeckillCloseException;
import org.kill.seckillenum.SeckillStateEnum;
import org.kill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }
    
    @RequestMapping(value="/{seckill_id}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckill_id") Long seckill_id, Model model){
        if (seckill_id == null) {
            return "redirect:/seckill_id/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckill_id);
        if (seckill == null) {
            return "forword:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }
    
    @ResponseBody
    @RequestMapping(value="/{seckill_id}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckill_id") Long seckill_id){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckill_id);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }
    
    @RequestMapping(value = "/{seckill_id}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckill_id") Long seckill_id,
            @PathVariable("md5") String md5,
            @CookieValue(value = "killphone",required = false) Long phone){
        
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        SeckillResult<SeckillExecution> result;
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckill_id, phone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatException e) {
            SeckillExecution execution = new SeckillExecution(seckill_id, SeckillStateEnum.DATE_REWRITE,null);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckill_id, SeckillStateEnum.END,null);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (Exception e) {
            logger.error(e.getMessage());
            SeckillExecution execution = new SeckillExecution(seckill_id, SeckillStateEnum.INNER_ERROR,null);
            return new SeckillResult<SeckillExecution>(false, execution);
        }
    }
    
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
