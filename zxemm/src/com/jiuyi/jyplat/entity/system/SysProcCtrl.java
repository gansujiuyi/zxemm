package com.jiuyi.jyplat.entity.system;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 系统批处理流程控制
 *  Aug 31, 2011  10:47:04 AM
 */
@Entity
public class SysProcCtrl implements Serializable {

	@Id
	private String procID;
	private String taskSeqNo;
	private String taskDec;
	private String taskType;
	private String taskCyc;
	private String task;
	private String para1;
	private String para2;
	private String para3;
	private String taskErrorLvl;
	private String workDate;
	private String procDate;
	private String procTime;
	private String taskProcState;
	private String taskErrorDesc;
	private String recState;

	public String getProcID() {
		return procID;
	}

	public void setProcID(String procID) {
		this.procID = procID;
	}

	public String getTaskSeqNo() {
		return taskSeqNo;
	}

	public void setTaskSeqNo(String taskSeqNo) {
		this.taskSeqNo = taskSeqNo;
	}

	public String getTaskDec() {
		return taskDec;
	}

	public void setTaskDec(String taskDec) {
		this.taskDec = taskDec;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskCyc() {
		return taskCyc;
	}

	public void setTaskCyc(String taskCyc) {
		this.taskCyc = taskCyc;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getPara1() {
		return para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public String getPara3() {
		return para3;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public String getTaskErrorLvl() {
		return taskErrorLvl;
	}

	public void setTaskErrorLvl(String taskErrorLvl) {
		this.taskErrorLvl = taskErrorLvl;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getProcDate() {
		return procDate;
	}

	public void setProcDate(String procDate) {
		this.procDate = procDate;
	}

	public String getProcTime() {
		return procTime;
	}

	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}

	public String getTaskProcState() {
		return taskProcState;
	}

	public void setTaskProcState(String taskProcState) {
		this.taskProcState = taskProcState;
	}

	public String getTaskErrorDesc() {
		return taskErrorDesc;
	}

	public void setTaskErrorDesc(String taskErrorDesc) {
		this.taskErrorDesc = taskErrorDesc;
	}

	public String getRecState() {
		return recState;
	}

	public void setRecState(String recState) {
		this.recState = recState;
	}

}
