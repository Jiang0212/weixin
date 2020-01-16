package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 计算商品有效期
 * @author Jiang
 *
 */
public class DateCal {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	long day = 0;
	long stateTimeLong;
	long endTimeLong;

	public long calDate(String endTime) {
		Date now = new Date();
		try {
			Date end = dateFormat.parse(endTime);
			stateTimeLong = now.getTime();
			endTimeLong = end.getTime();
			// 结束时间-开始时间 = 天数
			day = (endTimeLong - stateTimeLong) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}
}
