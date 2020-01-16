package dao.impl;

import dao.GoodsDao;
import domain.Goods;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import util.DateCal;
import util.JDBCUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsDaoImpl implements GoodsDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	DateCal datecal = new DateCal();

	/**
	 * 查询所有商品信息
	 */
	@Override
	public List<Goods> findall(String markid) {
		String sql = "select * from " + markid;
		List<Goods> list = template.query(sql, new BeanPropertyRowMapper<Goods>(Goods.class));
		return list;
	}

	/**
	 * 根据商品id删除商品
	 */
	@Override
	public int delete(String id,String markid) {
		// 1.定义sql
		String sql = "delete from "+markid+" where id = ?";
		// 2.执行sql
		return template.update(sql, id);
	}

	/**
	 * 根据商品信息查询单个商品信息
	 */
	@Override
	public Goods findgoods(String id,String markid) {
		String sql = "select * from "+markid+" where id = ?";
		try {
			return template.queryForObject(sql, new BeanPropertyRowMapper<Goods>(Goods.class), id);
		} catch (EmptyResultDataAccessException e) {
            return null;
		}
	}

	/**
	 * 根据商品id修改单个商品信息
	 * 返回值小于1则修改失败
	 */
	@Override
	public int updategoods(Goods goods,String markid) {
		String sql = "update "+markid +" set  name=? , size=?, unit=?, day=?, validTime=? where id=?";
		return template.update(sql, goods.getName(), goods.getSize(), goods.getUnit(), goods.getDay(), goods.getValidTime(), goods.getId());
	}

	/**
	 * 增加单个商品
	 * 返回值小于1则插入失败
	 */
	@Override
	public int addgoods(Goods goods, String markid) {
		String sql = "insert into "+markid+" (id, name, size, unit, day, validTime) values(?,?,?,?,?,?)";
		return template.update(sql, goods.getId(), goods.getName(), goods.getSize(), goods.getUnit(),goods.getDay(), goods.getValidTime());
	}

	/**
	 * 将商品转换为map集合
	 */
	@Override
	public Map<String, String> toMap(Goods goods) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("msg", "1");
		params.put("id", goods.getId());
		params.put("name", goods.getName());
		params.put("size", goods.getSize());
		params.put("unit", goods.getUnit());
		params.put("day", goods.getDay());
		params.put("validTime", goods.getValidTime());
		return params;
	}

	/**
	 * 更新所有商品的有效期
	 */
	@Override
	public void updateDay(String markid) {
		List<Goods> list = findall(markid);
		Goods good = new Goods();
		for(int i = 0;i < list.size();i++){
		    good = list.get(i);//直接拿这个a去点get或者set就行了
		    good.setDay(datecal.calDate(good.getValidTime())+"");
		    updategoods(good,markid);
		}
	}

}
