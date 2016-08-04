package com.wondersgroup.qyws.tjfx.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.qyws.tjfx.service.CommonService;

@Service("CommonServiceImplService")
@Transactional
public class CommonServiceImpl extends ServiceImpl implements CommonService {

	/**
	 * 查询字典MAP
	 */
	public Map selectDicInfo(String column){
		Map<String,String> map = new LinkedHashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select aaa102 as key,aaa103 as value from aa10 where aaa100='"+column+"' and aae100 = '1' ");
		try {
			List<Map<String,String>> list = this.baseDao.findListOfMapBySql(sb.toString());
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,String> tempMap = (Map<String,String>)list.get(i);
					map.put(tempMap.get("KEY"), tempMap.get("VALUE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
