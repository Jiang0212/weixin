package service;

import domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    public List<Goods> findall(String makrid);

    public Map<String, String> toMap(Goods goods);
    
    void delSelectedgoods(String[] ids,String makrid);
    
    public int deleteOnegoods(String id,String makrid);

    public Goods findgoods(String id,String makrid);

    public int updategoods(Goods goods,String makrid);

    public int addgoods(Goods goods,String makrid);
    
    public void updateDay(String makrid);
}
