package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.RunningDevice;
import org.springframework.stereotype.Repository;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:37
 */
@Repository
public interface CommonMeterMapper {
    RunningDevice getRunningCnt(int cId);
}
