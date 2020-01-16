package service.impl;

import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import domain.Goods;
import service.GoodsService;

import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService {

   private GoodsDao dao=new GoodsDaoImpl();

    @Override
    public List<Goods> findall(String makrid) {
        return dao.findall(makrid);
    }

    @Override
    public void delSelectedgoods(String[] ids,String makrid) {
        if(ids != null && ids.length > 0){
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                dao.delete(id,makrid);
            }
        }
    }

    @Override
    public Goods findgoods(String id,String makrid) {
        return dao.findgoods(id,makrid);
    }

    @Override
    public int updategoods(Goods goods,String makrid) {
       return dao.updategoods(goods,makrid);
    }

    @Override
    public int addgoods(Goods goods,String makrid) {
       return dao.addgoods(goods,makrid);
    }

	@Override
	public Map<String, String> toMap(Goods goods) {
		return dao.toMap(goods);
	}

	@Override
	public void updateDay(String makrid) {
		dao.updateDay(makrid);
	}

	@Override
	public int deleteOnegoods(String id,String makrid) {
		return dao.delete(id,makrid);
	}
}
