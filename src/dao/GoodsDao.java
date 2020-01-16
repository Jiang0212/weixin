package dao;

import domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsDao {

    public List<Goods> findall(String markid);
    
    public Map<String, String> toMap(Goods goods);

    public int delete(String id,String markid);

    public Goods findgoods(String id,String markid);

    public int updategoods(Goods goods,String markid);

    public int addgoods(Goods goods,String markid);
    
    
    public void updateDay(String markid);
}
