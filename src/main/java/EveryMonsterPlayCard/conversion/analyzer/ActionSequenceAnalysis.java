package EveryMonsterPlayCard.conversion.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 动作序列分析结果
 * 存储动作序列的分析信息
 */
public class ActionSequenceAnalysis {
    private int totalActions;
    private List<SingleActionAnalysis> actionAnalyses;
    private String sequenceType;
    private String analysisSummary;
    private long analysisTime;
    
    public ActionSequenceAnalysis() {
        this.totalActions = 0;
        this.actionAnalyses = new ArrayList<>();
        this.sequenceType = "UNKNOWN";
        this.analysisTime = System.currentTimeMillis();
    }
    
    public ActionSequenceAnalysis(List<SingleActionAnalysis> actionAnalyses) {
        this();
        this.actionAnalyses = actionAnalyses != null ? new ArrayList<>(actionAnalyses) : new ArrayList<>();
        this.totalActions = this.actionAnalyses.size();
    }
    
    public int getTotalActions() {
        return totalActions;
    }
    
    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }
    
    public List<SingleActionAnalysis> getActionAnalyses() {
        return actionAnalyses;
    }
    
    public void setActionAnalyses(List<SingleActionAnalysis> actionAnalyses) {
        this.actionAnalyses = actionAnalyses != null ? new ArrayList<>(actionAnalyses) : new ArrayList<>();
        this.totalActions = this.actionAnalyses.size();
    }
    
    public String getSequenceType() {
        return sequenceType;
    }
    
    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }
    
    public String getAnalysisSummary() {
        return analysisSummary;
    }
    
    public void setAnalysisSummary(String analysisSummary) {
        this.analysisSummary = analysisSummary;
    }
    
    public long getAnalysisTime() {
        return analysisTime;
    }
    
    public void setAnalysisTime(long analysisTime) {
        this.analysisTime = analysisTime;
    }
    
    /**
     * 添加动作分析
     * @param analysis 动作分析
     */
    public void addActionAnalysis(SingleActionAnalysis analysis) {
        if (analysis != null) {
            this.actionAnalyses.add(analysis);
            this.totalActions = this.actionAnalyses.size();
        }
    }
    
    /**
     * 获取指定索引的动作分析
     * @param index 索引
     * @return 动作分析
     */
    public SingleActionAnalysis getActionAnalysis(int index) {
        if (index >= 0 && index < actionAnalyses.size()) {
            return actionAnalyses.get(index);
        }
        return null;
    }
    
    /**
     * 获取动作分析数量
     * @return 数量
     */
    public int getActionCount() {
        return actionAnalyses.size();
    }
    
    /**
     * 检查是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return actionAnalyses.isEmpty();
    }
    
    /**
     * 清空所有动作分析
     */
    public void clear() {
        actionAnalyses.clear();
        totalActions = 0;
    }
    
    @Override
    public String toString() {
        return "ActionSequenceAnalysis{" +
                "totalActions=" + totalActions +
                ", actionAnalyses=" + actionAnalyses +
                ", sequenceType='" + sequenceType + '\'' +
                ", analysisSummary='" + analysisSummary + '\'' +
                ", analysisTime=" + analysisTime +
                '}';
    }
}