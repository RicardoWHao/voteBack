package com.lingling.controller.votediv;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.votediv.VoteDivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
public class VoteDivController extends BaseController{
    @Autowired
    VoteDivService voteDivService;

    public int deleteByPrimaryKey(String id){
        return voteDivService.deleteByPrimaryKey(id);
    }

    public int insert(VoteDiv record){

        return voteDivService.insert(record);
    }
    @RequestMapping("votediv/selectById")
    public VoteDiv selectByPrimaryKey(String id){
        return voteDivService.selectByPrimaryKey(id);
    }

    @RequestMapping("votediv/selectAll")
    public List<VoteDiv> selectAll(){
        return voteDivService.selectAll();
    }

    public int updateByPrimaryKey(VoteDiv record){
        return voteDivService.updateByPrimaryKey(record);
    }
}
