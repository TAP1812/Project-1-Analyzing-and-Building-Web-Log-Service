package hust.soict.cybersec.project1.stufftesting;

public class TestGeoIP {
	public static void main(String[] args) {
		try {
            // Đường dẫn tới file GeoLite2-Country.mmdb
            File database = new File("path/to/GeoLite2-Country.mmdb");

            // Tạo DatabaseReader
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // Địa chỉ IP bạn muốn tra cứu
            InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

            // Truy vấn thông tin quốc gia từ địa chỉ IP
            CountryResponse response = reader.country(ipAddress);

            // Lấy mã quốc gia
            String countryCode = response.getCountry().getIsoCode();
            System.out.println("Country Code: " + countryCode);

            // Lấy tên quốc gia
            String countryName = response.getCountry().getName();
            System.out.println("Country Name: " + countryName);

        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }
	}
}
