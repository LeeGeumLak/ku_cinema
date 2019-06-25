package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.PaymentInfo;

public interface PaymentInfoDao{
	
	public boolean insert(PaymentInfo payment);
	public List<PaymentInfo> getAllPaymentInfo();
	public boolean delete(String paymentId);
	public boolean update(PaymentInfo payment);
	public PaymentInfo getPaymentById(String paymentId);

}
