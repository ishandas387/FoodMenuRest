package com.menu.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemGroupResponseDTO {
	
	private StatusCode status;
	private String message;
	private String groupedBy;
	private Map<String,List<ItemDTO>> itemdtoMap;
	public StatusCode getStatus() {
		return status;
	}
	public void setStatus(StatusCode status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGroupedBy() {
		return groupedBy;
	}
	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}
	
	public Map<String, List<ItemDTO>> getItemdtoMap() {
		return itemdtoMap;
	}
	public void setItemdtoMap(Map<String, List<ItemDTO>> itemdtoMap) {
		this.itemdtoMap = itemdtoMap;
	}
}
