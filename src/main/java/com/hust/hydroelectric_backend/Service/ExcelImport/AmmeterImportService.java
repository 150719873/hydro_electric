package com.hust.hydroelectric_backend.Service.ExcelImport;

import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
import com.hust.hydroelectric_backend.Dao.BlockMapper;
import com.hust.hydroelectric_backend.Dao.UserMapper;
import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Block;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.utils.ExcelImportUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:05
 * 电表导入相关操作
 */
@Service
public class AmmeterImportService extends ImportBase {

    @Resource
    UserMapper userMapper;

    @Resource
    BlockMapper blockMapper;

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;

    // todo 待完善
    @Override
    public ResultData read(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        System.out.println("开始导入");
        Object[] obs = ExcelImportUtil.readSheets(is, isExcel2003);
        List<List<String>>[] data = (List<List<String>>[]) obs[0];
        String[] blockNames = (String[]) obs[1];
        if (data.length != blockNames.length) return Result.error(HttpStatus.BAD_REQUEST, "sheet数目错误");
        for (int k = 0; k < data.length; k++) {
            String blockName = blockNames[k];
            List<List<String>> dataList = data[k];
            Block block = blockMapper.findByBlockNameAndCid(blockName, communityId);
            int bid = -1;
            /**
             * 不存在则创建楼栋
             */
            if (block == null) {
                Block curBlock = new Block();
                curBlock.setbName(blockName);
                curBlock.setcId(communityId);
                try {
                    blockMapper.saveBlock(curBlock);
                    bid = curBlock.getbId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                bid = block.getbId();
            }
            for (int i = 2; i < dataList.size(); i++) {//循环每一行，即对应单个用户
                List<String> cellList = dataList.get(i);
                String uname = cellList.get(0);
                String tel = cellList.get(1);
                int meterType = Integer.valueOf(cellList.get(2));
                String uaddr = cellList.get(4);
                String meterNo = cellList.get(5);
                int caliber = Integer.valueOf(cellList.get(6));
                int valve = Integer.valueOf(cellList.get(7));
                BigDecimal readValue = new BigDecimal(cellList.get(8));
                /**
                 * 插入User表
                 */
                User user = userMapper.findByUnameAndTelAndEnprNo(uname, tel, enprNo);
                int uid = -1;
                if (user == null) {
                    User curUser = new User();
                    curUser.setuName(uname);
                    curUser.setuTel(tel);
                    curUser.setbId(bid);
                    curUser.setAddress(uaddr);
                    curUser.setAccountBalance(BigDecimal.ZERO);
                    curUser.setEnprNo(enprNo);
                    try{
                        userMapper.saveUser(curUser);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    uid = curUser.getuId();
                } else {
                    uid = user.getuId();
                }

                /**
                 * 插入水表
                 */
                Watermeter meter = new Watermeter();
                meter.setMeterNo(meterNo);
                meter.setuId(uid);
                meter.setcId(communityId);
                meter.setCaliber(caliber);
                long curUnixTime = System.currentTimeMillis()/1000;
                meter.setInstallTime(curUnixTime);
                meter.setReadTime(curUnixTime);
                meter.setReadValue(readValue);
                meter.setPreReadTime(curUnixTime);
                meter.setPreReadValue(readValue);
                meter.setMonthAmount(BigDecimal.ZERO);
                meter.setState(0);
                meter.setMeterType(meterType);
                meter.setValve(valve);
                meter.setEnprNo(enprNo);
                try {
                    waterMeterMapper.saveMeter(meter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success("导入成功");
    }

    //todo 待完善
    @Override
    public ResultData check(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        return null;
    }
}
