package vn.vnest.request;

public class DeviceLoginRequest extends BaseRequest {
	private int dId,versionCode,sdk;
	private String base, board, brand, deviceId, fingerPrint, host, id, incremental, model, type, user;

	public DeviceLoginRequest(int dId, String base, String board, String brand, String deviceId, String fingerPrint,
			String host, String id, String incremental, String model, String type, String user, int sdk,int versionCode) {
		super();
		this.dId = dId;
		this.base = base;
		this.board = board;
		this.brand = brand;
		this.deviceId = deviceId;
		this.fingerPrint = fingerPrint;
		this.host = host;
		this.id = id;
		this.incremental = incremental;
		this.model = model;
		this.type = type;
		this.user = user;
		this.sdk = sdk;
		this.versionCode = versionCode;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIncremental() {
		return incremental;
	}

	public void setIncremental(String incremental) {
		this.incremental = incremental;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getSdk() {
		return sdk;
	}

	public void setSdk(int sdk) {
		this.sdk = sdk;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	
}
