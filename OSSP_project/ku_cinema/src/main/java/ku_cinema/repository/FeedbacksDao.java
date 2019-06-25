package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.Feedbacks;

public interface FeedbacksDao{
	
	public boolean insert(Feedbacks feed);
	public List<Feedbacks> getAllFeedbacks();
	public boolean delete(long feedId);
}
