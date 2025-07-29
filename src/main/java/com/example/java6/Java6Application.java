package com.example.java6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java6Application {




        public static void main(String[] args) {
            // Khởi chạy ứng dụng Spring Boot
            SpringApplication.run(Java6Application.class, args);

            // In thông báo về trạng thái của ứng dụng
            System.out.println("🚀 Ứng dụng đã khởi chạy...");
            System.out.println("✨ Chúc ông chủ may mắn và luôn gặp điều tốt lành!");

            // Các mã trạng thái HTTP
            System.out.println("✅ 200 OK: Yêu cầu thành công, dữ liệu được trả về đúng.");
            System.out.println("✅ 201 Created: Yêu cầu POST thành công, dữ liệu mới đã được tạo.");
            System.out.println("⚠️ 400 Bad Request: Yêu cầu không hợp lệ (có thể do thiếu tham số, hoặc sai định dạng).");
            System.out.println("⚠️ 404 API không tồn tại");
            System.out.println("❌ 500 Internal Server Error: Lỗi phía server (thường là lỗi hệ thống hoặc lỗi cấu hình).");
        }





}
