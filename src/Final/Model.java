package Final;

public class Model {
	private int rank_id;
	private String rank_name;
	private int rank_score;
	
	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Model(String rank_name, int rank_score) {
		super();
		this.rank_name = rank_name;
		this.rank_score = rank_score;
	}

	

	public Model(int rank_id, String rank_name, int rank_score) {
		super();
		this.rank_id = rank_id;
		this.rank_name = rank_name;
		this.rank_score = rank_score;
	}

	public Model(int rank_id) {
		super();
		this.rank_id = rank_id;
	}



	public String getRank_name() {
		return rank_name;
	}



	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}



	public int getRank_score() {
		return rank_score;
	}



	public void setRank_score(int rank_score) {
		this.rank_score = rank_score;
	}



	public int getRank_id() {
		return rank_id;
	}



	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}

	@Override
	public String toString() {
		return rank_name + " : " + rank_score;
	}
	
	
}
