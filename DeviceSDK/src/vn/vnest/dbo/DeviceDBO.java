package vn.vnest.dbo;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import vn.vnest.entities.InfoAgent;
import vn.vnest.entities.InfoAppointment;
import vn.vnest.entities.InfoDevice;
import vn.vnest.request.ActiveDeviceRequest;
import vn.vnest.request.AgentAccountRequest;
import vn.vnest.request.AgentLoginRequest;
import vn.vnest.request.CreateActivationCodeRequest;
import vn.vnest.request.CreateAgentRequest;
import vn.vnest.request.DeviceLoginRequest;
import vn.vnest.request.DeviceServiceRequest;
import vn.vnest.request.HistoryAppointmentRequest;

public class DeviceDBO {
	private static final Logger log = LogManager.getLogger(DeviceDBO.class);
	
	MemCachedClient mcc;
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
		String pattern = "yyyyMMdd";
		DateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
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

	public static ArrayList<InfoDevice> getDeivceInfo(String idDevice) throws Exception {
		ArrayList<InfoDevice> infoDeivces = new ArrayList<InfoDevice>();
		Connection connection = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			connection = DeviceDataSource.getInstance().getConnection();
			if (connection != null) {
				String sql = "{Call GetInfoDevice(?)}";
				st = connection.prepareCall(sql);
				st.clearParameters();
				st.setString(1, idDevice);
				rs = st.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						int dId = rs.getInt("dId");
						String base = rs.getString("base");
						String board = rs.getString("board");
						String brand = rs.getString("brand");
						String deviceId = rs.getString("deviceId");
						String fingerPrint = rs.getString("fingerPrint");
						String host = rs.getString("host");
						String id = rs.getString("id");
						String incremental = rs.getString("incremental");
						String model = rs.getString("model");
						String type = rs.getString("type");
						String user = rs.getString("user");
						String status = rs.getString("status");
						InfoDevice info = new InfoDevice(dId, base, board, brand, deviceId, fingerPrint, host, id,
								incremental, model, type, user, status);
						infoDeivces.add(info);
					}
				}
			} else {
				log.info("connection is null");
			}
		} catch (SQLException e) {
			log.info("", e);
		} finally {
			rs.close();
			st.close();
			connection.close();
		}

		return infoDeivces;

	}

	public static int deviceLogin(DeviceLoginRequest deviceLogin) throws Exception {
		int res = 0;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call deviceLogin(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				cs = con.prepareCall(sql);
				cs.clearParameters();
				cs.setString(1, deviceLogin.getBase());
				cs.setString(2, deviceLogin.getBoard());
				cs.setString(3, deviceLogin.getBrand());
				cs.setString(4, deviceLogin.getDeviceId());
				cs.setString(5, deviceLogin.getFingerPrint());
				cs.setString(6, deviceLogin.getHost());
				cs.setString(7, deviceLogin.getId());
				cs.setString(8, deviceLogin.getIncremental());
				cs.setString(9, deviceLogin.getModel());
				cs.setInt(10, deviceLogin.getSdk());
				cs.setInt(11, deviceLogin.getVersionCode());
				cs.setString(12, deviceLogin.getType());
				cs.setString(13, deviceLogin.getUser());

				rs = cs.executeQuery();
				if (rs != null && rs.next()) {
					res = rs.getInt("res");
				}

			} else {
				log.info("connection is null");

			}
		} catch (Exception e) {
			log.info("ex: " + e.toString(), e);
		} finally {
			rs.close();
			cs.close();
			con.close();
		}
		return res;
	}

	public static int historyAppointment(HistoryAppointmentRequest historyAppointment) throws Exception {

		int res = 0;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call historyAppointment(?,?,?,?,?,?)}";
				cs = con.prepareCall(sql);
				cs.clearParameters();
				cs.setString(1, historyAppointment.getDeviceId());
				cs.setString(2, historyAppointment.getContent());
				cs.setTimestamp(3, parseDatePost(historyAppointment.getStartDate()));
				cs.setTimestamp(4, parseDatePost(historyAppointment.getEndDate()));
				cs.setString(5, historyAppointment.getQuestion());
				cs.setInt(6, historyAppointment.getStatus());

				rs = cs.executeQuery();
				if (rs != null && rs.next()) {
					res = rs.getInt("res");
				}

			} else {
				log.info("connection is null");

			}
		} catch (Exception e) {
			log.info("ex: " + e.toString(), e);
		} finally {
			rs.close();
			cs.close();
			con.close();
		}
		return res;
	}

	public static ArrayList<InfoAppointment> getInfoAppointment(String id, String startdate, String enddate)
			throws Exception {
		ArrayList<InfoAppointment> infoAppointment = new ArrayList<InfoAppointment>();
		Connection connection = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			connection = DeviceDataSource.getInstance().getConnection();
			if (connection != null) {
				String sql = "{Call GetInfoAppointment(?,?,?)}";
				st = connection.prepareCall(sql);
				st.clearParameters();
				st.setString(1, id);
				st.setString(2, parseDateGet(startdate));
				st.setString(3, parseDateGet(enddate));
				rs = st.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						String deviceId = rs.getString("deviceId");
						String content = rs.getString("content");
						String startDate = rs.getString("startDate");
						String endDate = rs.getString("endDate");
						String question = rs.getString("question");
						String status = rs.getString("status");

						InfoAppointment info = new InfoAppointment(deviceId, content, startDate, endDate, question,
								status);
						infoAppointment.add(info);
					}
				}
			} else {
				log.info("connection is null");
			}
		} catch (SQLException e) {
			log.info("", e);
		} finally {
			rs.close();
			st.close();
			connection.close();
		}

		return infoAppointment;

	}

	public static int deviceService(DeviceServiceRequest deviceService) throws Exception {
		int res = 0;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call insertDevicesService(?,?,?,?,?,?,?,?)}";
				cs = con.prepareCall(sql);
				cs.clearParameters();
				cs.setString(1, deviceService.getDeviceId());
				cs.setString(2, deviceService.getAction());
				cs.setTimestamp(3, parseDatePost(deviceService.getStartDate()));
				cs.setTimestamp(4, parseDatePost(deviceService.getEndDate()));
				cs.setBigDecimal(5,
						deviceService.getAmount().compareTo(new BigDecimal(0)) > 0 ? deviceService.getAmount()
								: new BigDecimal(0));
				cs.setInt(6, deviceService.getCount() > 0 ? deviceService.getCount() : 1);
				cs.setInt(7, deviceService.getQuantity() > 0 ? deviceService.getQuantity() : 1);
				cs.setString(8, deviceService.getQuestion());
				rs = cs.executeQuery();
				if (rs != null && rs.next()) {
					res = rs.getInt("res");
				}
			} else {
				log.info("connection is null");
			}
		} catch (Exception e) {
			log.info("ex" + e.toString(), e);
		} finally {
			rs.close();
			cs.close();
			con.close();
		}

		return res;
	}

	public static ArrayList<DeviceServiceRequest> getInfoDeviceService(String id, String stDate, String eDate,
			String at, String c, String q, String mount) throws Exception {
		ArrayList<DeviceServiceRequest> infoDeviceService = new ArrayList<DeviceServiceRequest>();
		Connection connection = null;
		CallableStatement st = null;
		ResultSet rs = null;

		try {
			connection = DeviceDataSource.getInstance().getConnection();
			if (connection != null) {
				String sql = "{Call getInfoDeviceService(?,?,?,?,?,?,?)}";
				st = connection.prepareCall(sql);
				st.clearParameters();
				st.setString(1, id);
				st.setString(2, stDate);
				st.setString(3, eDate);
				st.setString(4, URLDecoder.decode(at, StandardCharsets.UTF_8.name()));
				st.setString(5, c);
				st.setString(6, q);
				st.setString(7, mount);
				rs = st.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						String deviceId = rs.getString("deviceId");
						String startDate = rs.getString("startDate");
						String endDate = rs.getString("endDate");
						String action = rs.getString("action");
						int count = rs.getInt("count");
						int quantity = rs.getInt("quantity");
						BigDecimal amount = rs.getBigDecimal("amount");
						DeviceServiceRequest info = new DeviceServiceRequest(deviceId, action, startDate, endDate,
								amount, count, quantity);
						infoDeviceService.add(info);
					}
				}
			} else {
				log.info("connection is null");
			}
		} catch (SQLException e) {
			log.info("", e);
		} finally {
			rs.close();
			st.close();
			connection.close();
		}

		return infoDeviceService;

	}

	public static String CreateActivationCode(CreateActivationCodeRequest codeRequest) {
		String res = "";
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String s = cached("", codeRequest.getPhone());
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call createActivationCode(?,?)}";
				cs = con.prepareCall(sql);
				cs.clearParameters();
				cs.setString(1, codeRequest.getPhone());
				cs.setString(2, s);
				rs = cs.executeQuery();
				if (rs != null && rs.next()) {
					res = rs.getString("res");
				}
			} else {
				log.info("connection is null");
			}
		} catch (Exception e) {
			log.info("", e);
		}

		return res;
	}

	public static String GetActivationCode(String phone) {
		String code = "";
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call getActivationCode(?)}";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, phone);
				rs = st.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						code = rs.getString("activationCode");
					}
				}
			}
			log.info("conection is null");

		} catch (Exception e) {
			log.info("", e);
		}
		return code;

	}

	public static int ActiveDevice(ActiveDeviceRequest req) {
		int res = 0;
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "{Call ActiveDevice(?,?,?)}";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, req.getPhone());
				st.setString(2, req.getActivationCode());
				st.setString(3, req.getDeviceId());
				rs = st.executeQuery();
				if (rs != null && rs.next()) {
					res = rs.getInt("res");
				}
			} else {
				log.info("conection is null");
			}
		} catch (Exception e) {
			log.info("", e);
		}
		return res;
	}

	public static ArrayList<InfoAgent> getAgent(String agent) {
		ArrayList<InfoAgent> agents = new ArrayList<InfoAgent>();
		Connection con = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if (con != null) {
				String sql = "Call getAgent(?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, agent);
				rs = st.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						String agentCode = rs.getString("agentCode");
						String name = rs.getString("name");
						String address = rs.getString("address");
						InfoAgent a = new InfoAgent(agentCode, name, address);
						agents.add(a);
					}
				}
			} else {
				log.info("conection is null");
			}
		} catch (Exception e) {
			log.info("", e);
		}
		return agents;
	}
	
	public static int createAgent(CreateAgentRequest request) {
		int res = 0;
		Connection con =null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if(con !=null) {
				String sql = "Call createAgent(?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getAgentCode());
				st.setString(2, request.getName());
				st.setString(3, request.getAddress());
				rs = st.executeQuery();
				if(rs!=null && rs.next()) {
					res = rs.getInt("res");
				}
			} else {
				log.info("conection is null");
			}
		} catch (Exception e) {
			log.info("",e);
		}
		return res;
		
	}
	
	public static int createAgentAccount(AgentAccountRequest request) {
		int res = 0;
		Connection con =null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if(con !=null) {
				String sql = "Call createAgentAccount(?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getAgentCode());
				st.setString(2, request.getUserName());
				st.setString(3, request.getPassWord());
				rs = st.executeQuery();
				if(rs!=null && rs.next()) {
					res = rs.getInt("res");
				}
			} else {
				log.info("conection is null");
			}
		} catch (Exception e) {
			log.info("",e);
		}
		return res;
		
	}
	
	public static int agentLogin(AgentLoginRequest request) {
		int res = 0;
		Connection con =null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			con = DeviceDataSource.getInstance().getConnection();
			if(con !=null) {
				String sql = "Call createAgent(?,?,?)";
				st = con.prepareCall(sql);
				st.clearParameters();
				st.setString(1, request.getAgentCode());
				st.setString(2, request.getUserName());
				st.setString(3, request.getPassWord());
				rs = st.executeQuery();
				if(rs!=null && rs.next()) {
					res = rs.getInt("res");
				}
			} else {
				log.info("conection is null");
			}
		} catch (Exception e) {
			log.info("",e);
		}

		cached(request.getUserName(),"");
		return res;
		
	}
	
	public static String  cached(String sql,String s) {
		String[] servers = {"localhost:11111"};
		SockIOPool pool = SockIOPool.getInstance("Login");
		pool.setServers( servers );
		pool.setFailover( true );
		pool.setInitConn( 10 );
		pool.setMinConn( 5 );
		pool.setMaxConn( 250 );
		pool.setMaintSleep( 30 );
		pool.setNagle( false );
		pool.setSocketTO( 3000 );
		pool.setAliveCheck( true );
		pool.initialize();
		MemCachedClient mcc = new MemCachedClient("Login");
		mcc.set(sql, "2231231312");
		String s1 = (String) mcc.get(s);
		return s1;
		
	}
}

