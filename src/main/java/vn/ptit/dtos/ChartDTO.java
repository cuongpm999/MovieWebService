package vn.ptit.dtos;

public class ChartDTO {
	private String label[];
	private double data[];

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	public ChartDTO() {
	}

	public String[] getLabel() {
		return label;
	}

	public void setLabel(String[] label) {
		this.label = label;
	}

	public ChartDTO(String[] label, double[] data) {
		super();
		this.label = label;
		this.data = data;
	}

}
