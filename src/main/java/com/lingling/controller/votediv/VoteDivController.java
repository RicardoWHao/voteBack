package com.lingling.controller.votediv;

import com.lingling.controller.base.BaseController;
import com.lingling.domin.votediv.VoteDiv;
import com.lingling.service.votediv.VoteDivService;
import com.lingling.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

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

    public VoteDiv selectByPrimaryKey(String id){
        return voteDivService.selectByPrimaryKey(id);
    }

    public List<VoteDiv> selectAll(){
        return voteDivService.selectAll();
    }

    public int updateByPrimaryKey(VoteDiv record){
        return voteDivService.updateByPrimaryKey(record);
    }
}
