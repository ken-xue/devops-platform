package io.kenxue.devops.coreclient.dto.common.command;

/**
 * 整个应用通用的Command
 */
public class CommonCommand extends Command{

    private String operator;
    private boolean needsOperator;

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        needsOperator = true;
    }

    public boolean isNeedsOperator(){
        return needsOperator;
    }
}
