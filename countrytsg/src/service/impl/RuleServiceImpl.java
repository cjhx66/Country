package service.impl;

import java.util.List;

import dao.RuleDao;
import entity.Rule;
import service.RuleService;

public class RuleServiceImpl implements RuleService {
	private RuleDao ruDao;

	public RuleDao getRuDao() {
		return ruDao;
	}

	public void setRuDao(RuleDao ruDao) {
		this.ruDao = ruDao;
	}

	@Override
	public String getRule() {
		// TODO Auto-generated method stub
		List<Rule> ru = ruDao.getRule();
		if (ru == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < ru.size(); i++) {
			str.append("{ruid:" + ru.get(i).getRuid() + ",runame:'"
					+ ru.get(i).getRuname() + "',ruday:" + ru.get(i).getRuday()
					+ ",runum:" + ru.get(i).getRunum() + ",renew:"
					+ ru.get(i).getRenew() + ",uname:'" + ru.get(i).getUserid()
					+ "',rutime:'"
					+ ru.get(i).getRutime().toString().substring(0, 19) + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + ru.size() + "}";
		return s;
	}

	@Override
	public String findRule(int ruid) {
		// TODO Auto-generated method stub
		Rule r = ruDao.findRule(ruid);
		if (r == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{ruid:" + r.getRuid() + ",runame:'" + r.getRuname()
				+ "',ruday:" + r.getRuday() + ",runum:" + r.getRunum()
				+ ",renew:" + r.getRenew() + "}");
		String s=str.toString();
		return s;
	}

	@Override
	public boolean addRule(Rule rule) {
		// TODO Auto-generated method stub
		return ruDao.addRule(rule);
	}

	@Override
	public boolean editRule(Rule rule) {
		// TODO Auto-generated method stub
		return ruDao.editRule(rule);
	}

	@Override
	public Rule finRule(int ruid) {
		// TODO Auto-generated method stub
		Rule r=ruDao.findRule(ruid);
		return r;
	}

	@Override
	public boolean delRule(int ruid) {
		// TODO Auto-generated method stub
		boolean b=ruDao.delRule(ruid);
		return b;
	}

}
