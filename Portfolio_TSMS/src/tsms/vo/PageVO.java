package tsms.vo;

import java.util.List;

public class PageVO {
	protected int where = 1;
	protected int startrow = 0;
	protected int endrow = 0;
	protected final int maxrows = 15;
	protected int totalrows = 0;
	protected int totalpages = 0;
	
	protected int totalgroup = 0;
	protected int maxpages = 2;
	protected int startpage = 1;
	protected int endpage = 10;
	protected int wheregroup = 1;
	protected int nextgroup = 0;
	protected int priorgroup = 0;
	
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	public int getTotalrows() {
		return totalrows;
	}
	public void setTotalrows(int totalrows) {
		this.totalrows = totalrows;
	}
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public int getTotalgroup() {
		return totalgroup;
	}
	public void setTotalgroup(int totalgroup) {
		this.totalgroup = totalgroup;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getWheregroup() {
		return wheregroup;
	}
	public void setWheregroup(int wheregroup) {
		this.wheregroup = wheregroup;
	}
	public int getNextgroup() {
		//return nextgroup;
		return wheregroup + 1;
	}
	public void setNextgroup(int nextgroup) {
		this.nextgroup = nextgroup;
	}
	public int getPriorgroup() {
		//return priorgroup;
		return wheregroup - 1;
	}
	public void setPriorgroup(int priorgroup) {
		this.priorgroup = priorgroup;
	}
	public int getMaxrows() {
		return maxrows;
	}
	public void setMaxpages(int maxpages) {
		this.maxpages = maxpages;
	}
	public int getMaxpages() {
		return maxpages;
	}
	
	public PageVO methodForPage(String go, String gogroup, List list) {
		PageVO page = new PageVO();
		
		if(go != null) {
			page.setWhere(Integer.parseInt(go));
			page.setWheregroup((page.getWhere()-1)/page.getMaxpages() +1);
			page.setStartpage((page.getWheregroup()-1)*page.getMaxpages() +1);
			page.setEndpage(page.getStartpage() + page.getMaxpages() -1);
		}else if(gogroup != null) {
			page.setWheregroup(Integer.parseInt(gogroup));
			page.setStartpage((page.getWheregroup()-1)*page.getMaxpages()+1);
			page.setWhere(page.getStartpage());
			page.setEndpage(page.getStartpage() + page.getMaxpages() -1);
		}
		
		if(list != null) {
			page.setTotalrows(list.size());
			page.setTotalpages((page.getTotalrows()-1)/page.getMaxrows()+1);
			page.setStartrow((page.getWhere()-1)*page.getMaxrows());
			page.setEndrow(page.getStartrow() + page.getMaxrows() - 1);
			if(page.getEndrow() >= page.getTotalrows()) {
				page.setEndrow(page.getTotalrows() - 1);
			}
			page.setTotalgroup(page.getTotalpages()/page.getMaxpages());
			if(page.getTotalpages() % page.getMaxpages() > 0) {
				page.setTotalgroup(page.getTotalgroup() + 1);
			}
			if(page.getEndpage() > page.getTotalpages()) {
				page.setEndpage(page.getTotalpages()); 
			}
		}
		
		return page;
	}
}
