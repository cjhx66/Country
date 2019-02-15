package dao;

import java.util.List;

import entity.Rule;

public interface RuleDao {
	public boolean addRule(Rule rule);//添加借阅规则
	public boolean editRule(Rule rule);//修改借阅规则
	public boolean delRule(int ruid);//删除借阅规则
	public List<Rule> getRule();//查找全部借阅规则
	public Rule findRule(int ruid);//查找单条借阅规则
}
