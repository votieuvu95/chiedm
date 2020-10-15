package vn.vnest.dbo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import redis.clients.jedis.Jedis;
import vn.vnest.cache.JedisFactory;
import vn.vnest.entities.ProduceUpdate;
import vn.vnest.main.Constant;
import vn.vnest.request.MarketRequest;
import vn.vnest.request.PriceMarketRequest;
import vn.vnest.request.UpdatePriceMarketRequest;
import vn.vnest.server.HttpClient;
import vn.vnest.utils.Utils;

public class MarketDBO {
	private static final Logger log = LogManager.getLogger(MarketDBO.class);

	public static Timestamp parseDatePost(String time) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Timestamp timestamp = null;
		try {
			if (time != null) {
				timestamp = new Timestamp(format.parse(time).getTime());
			}

		} catch (ParseException e) {
			log.info("", e);
		}
		return timestamp;
	}

	public static String parseDateGet(String time) {
		String pattern = "yyyyMMddHHmmss";
		DateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = "";
		try {
			if (time != null) {
				Date date = format.parse(time);
				datetime = dateformat.format(date);
			}

		} catch (ParseException e) {
			log.info("", e);
		}
		return datetime;
	}

	public static int postMarket(MarketRequest request) throws Exception {
		int i = 0;
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call insertMarket(?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getName());
				st.setString(2, request.getCode());
				st.setString(3, request.getAddress());
				rs = st.executeQuery();
				if (rs != null && rs.next()) {
					i = rs.getInt("res");
				}

			} else {
				log.info("conection is null");
			}

		} catch (SQLException e) {
			log.info(" ex :" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return i;

	}

	public static int postPriceMarket(PriceMarketRequest request) throws SQLException {
		int i = 0;
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();

			if (con != null) {
				String sql = "Call insertPriceMarket(?,?,?,?,?,?,?,?)";
				st = con.prepareCall(sql);
				st.setString(1, request.getCode());
				st.setString(2, request.getCodeMartket());
				st.setString(3, request.getPrice());
				st.setString(4, request.getCraetedDate());
				st.setString(5, request.getUpdatedDate());
				st.setString(6, request.getPriceSale());
				st.setString(7, request.getInfo());
				st.setString(8, request.getName());
				rs = st.executeQuery();

				if (rs != null && rs.next()) {
					i = rs.getInt("res");
				}

			} else {
				log.info("connection is null");
			}
		} catch (Exception e) {
			log.info("ex:" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return i;
	}
	
	public static List<ProduceUpdate> getProductUpdate( String codeMarket) throws Exception {
		List<ProduceUpdate> product = new ArrayList<>();
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call getProductUpdate(?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, codeMarket);
				rs = st.executeQuery();
				while (rs.next()) {
					String code = rs.getString("codeProduct");
					String name = rs.getString("name");
					String pricesale = rs.getString("pricesale");
					String price = rs.getString("priceProduct");
					ProduceUpdate pr = new  ProduceUpdate(price,name,pricesale,code);
					product.add(pr);
				}

			} else {
				log.info("conection is null");
			}

		} catch (SQLException e) {
			log.info(" ex :" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return product;

	}
	
	public static List<ProduceUpdate> getListProduct(String codeMarket, String products) throws Exception {
		List<ProduceUpdate> product = new ArrayList<>();
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call getListProductMarket(?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, codeMarket);
				st.setString(2, products);
				rs = st.executeQuery();
				while (rs.next()) {
					String code = rs.getString("codeProduct");
					String name = rs.getString("name");
					String pricesale = rs.getString("pricesale");
					String price = rs.getString("priceProduct");
					ProduceUpdate pr = new  ProduceUpdate(price,name,pricesale,code);
					product.add(pr);
				}

			} else {
				log.info("conection is null");
			}

		} catch (SQLException e) {
			log.info(" ex :" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return product;

	}
	
	public static int updateMarket(MarketRequest request) throws Exception {
		int i = 0;
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call updateMarket(?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getName());
				st.setString(2, request.getCode());
				st.setString(3, request.getAddress());
				rs = st.executeQuery();
				if (rs != null && rs.next()) {
					i = rs.getInt("res");
				}

			} else {
				log.info("conection is null");
			}

		} catch (SQLException e) {
			log.info(" ex :" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return i;

	}
	
	public static int updatePriceMarket(UpdatePriceMarketRequest request) throws Exception {
		int i = 0;
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			con = MarketDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call updatePriceMartket(?,?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getCodeProduct());
				st.setString(2, request.getPrice());
				st.setString(3, request.getPriceSale());
				st.setString(4, request.getCodeMarket());
				rs = st.executeQuery();
				if (rs != null && rs.next()) {
					i = rs.getInt("res");
				}

			} else {
				log.info("conection is null");
			}

		} catch (SQLException e) {
			log.info(" ex :" + e.toString());
		} finally {
			rs.close();
			st.close();
			con.close();
		}
		return i;

	}

}
