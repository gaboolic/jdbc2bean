package tk.gbl.core;

import java.util.List;
import java.util.Map;

public class MainTank {
	
	Message message = new Message();
	ToBean toBean;
	
	public void dododo() throws Exception{
		message.init();
		List<String> tables = message.getTableList();
		
		for (int i = 0; i < tables.size(); i++) {
			String tableName = tables.get(i);
			//System.out.println(tableName);
			Map<String,String> tableField = message.getField(tableName);

			toBean = new ToBean(tableName,tableField);
			toBean.execute();
		}
	}
	
	
	
	

}
