package com.hust.hydroelectric_backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.hydroelectric_backend.Service.WechatpayService;
import com.hust.hydroelectric_backend.Entity.Payhistory;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Service.PayService;
import com.hust.hydroelectric_backend.Service.UserService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:42
 * 用户相关操作
 */
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PayService payService;

    @Autowired
    WechatpayService wechatpayService;

    /**
     * 获取用户详细信息
     */
    @GetMapping("/findUser")
    public ResultData getUser(@RequestParam(name = "uId", defaultValue = "-1") int id,
                              @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> userService.findByUserIdAndEnprNo(id, enprNo));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delUser")
    public ResultData delUser(@RequestParam(name = "uId", defaultValue = "-1") int id,
                              @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> userService.delUserById(id, enprNo));
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/uptUser")
    public ResultData uptUser(@RequestBody User user) {
        return ResponseHandler.doHandle(() -> userService.uptUser(user));
    }

    /**
     * 根据楼栋获取用户相关信息
     */
    @GetMapping("/GetUserInfoByBlockId")
    public ResultData getUserInfoByBlockId(@RequestParam(value = "bId", defaultValue = "-1") int bId,
                                           @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        return ResponseHandler.doHandle(() -> userService.getUserInfoByBlockId(bId, pageNum, pageSize));
    }

    /**
     * 根据用户id获取用户相关信息
     * mark一下，测试结果为空
     */
    @GetMapping("/GetUserInfoByUid")
    public ResultData getUserInfoByUid(@RequestParam(value = "uId", defaultValue = "-1") int uid) {
        return ResponseHandler.doHandle(() -> userService.getUserInfoByUid(uid));
    }

    /**
     * 根据用户姓名获取用户相关信息
     */
    @GetMapping("/GetUserInfoByUname")
    public ResultData getUserInfoByUname(@RequestParam(value = "uName") String uname,
                                         @RequestParam("enprNo") String enprNo,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        if (StringUtils.isNotBlank(uname) && StringUtils.isNotBlank(enprNo))
            return ResponseHandler.doHandle(() -> userService.getUserInfoByUname(uname, enprNo, pageNum, pageSize));
        return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
    }

    /**
     * 根据表地址获取用户相关信息
     */
    @GetMapping("/GetUserInfoByMeterNo")
    public ResultData getUserInfoByMeterNo(@RequestParam(value = "meterNo", defaultValue = "-1") String meterNo,
                                           @RequestParam("meterType") int meterType,
                                           @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> userService.getUserInfoByMeterNo(meterNo, meterType, enprNo));
    }

    /**
     * 查看用户的水费、电费每天扣费记录
     */
    @GetMapping("/user/dailyCost")
    public ResultData userDailyCost(@RequestParam("uId") int uid,
                                    @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> userService.userDailyCost(uid, enprNo));
    }

    /**
     * 用户缴费
     */
    @PostMapping("/user/pay")
    public ResultData userPay(@RequestBody Payhistory payhistory) {
        return ResponseHandler.doHandle(() -> payService.paySave(payhistory));
    }

    /**
     * 微信缴费
     */
    @PostMapping("/wechat/pay")
    public ResultData wechatPay(@RequestBody String msg) {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String authcode = jsonObject.getString("authCode");
        String fee = jsonObject.getString("fee");
        String enprNo = jsonObject.getString("fee");
        int uid = jsonObject.getInteger("enprNo");
        return ResponseHandler.doHandle(() -> wechatpayService.doMicroOrder(authcode, fee, uid, enprNo));
    }

    /**
     * 获得用户缴费记录
     */
    @GetMapping("/GetUserPayHistory")
    public ResultData getUserPayHistory(@RequestParam("uId") int uId,
                                        @RequestParam(value = "startDateLine", defaultValue = "-1") long startLine,
                                        @RequestParam(value = "endDateLine", defaultValue = "-1") long endLine,
                                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        return ResponseHandler.doHandle(() -> payService.getUserPayHistory(uId, startLine, endLine, pageNum, pageSize));
    }




}
