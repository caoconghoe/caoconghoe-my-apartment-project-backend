# Scenario cho chức năng 11, 12, 13

## Bổ sung cho Khách hàng

### 2.3 Scenario khách hàng gửi yêu cầu thuê

1. Khách hàng A đăng nhập thành công và chọn chức năng tìm kiếm tòa nhà.
2. Hệ thống hiển thị danh sách tòa nhà phù hợp với nhu cầu tìm kiếm.
3. A chọn một tòa nhà và nhấn "Xem chi tiết".
4. Hệ thống mở trang chi tiết tòa nhà, hiển thị thông tin tòa nhà và khu vực "Gửi yêu cầu thuê".
5. Hệ thống tải danh sách diện tích thuê còn trống của tòa nhà.
6. A chọn diện tích có sẵn hoặc nhập diện tích mong muốn, nhập ngày bắt đầu dự kiến, số tháng thuê dự kiến và ghi chú.
7. A nhấn nút "Gửi yêu cầu thuê".
8. Hệ thống kiểm tra dữ liệu yêu cầu thuê.
8.1. Nếu khách hàng chưa chọn diện tích và cũng chưa nhập diện tích mong muốn, hệ thống báo lỗi và không lưu yêu cầu.
8.2. Nếu diện tích hoặc số tháng thuê nhỏ hơn hoặc bằng 0, hệ thống báo lỗi và yêu cầu nhập lại.
8.3. Nếu diện tích được chọn không thuộc tòa nhà hiện tại hoặc không còn trạng thái available, hệ thống báo lỗi.
9. Hệ thống lưu yêu cầu thuê với trạng thái pending.
10. Hệ thống thông báo gửi yêu cầu thuê thành công.

### 2.4 Scenario khách hàng xem danh sách yêu cầu thuê của mình

1. Khách hàng A đăng nhập thành công và chọn mục "Yêu cầu của tôi".
2. Hệ thống hiển thị danh sách các yêu cầu thuê mà A đã gửi.
3. Mỗi yêu cầu hiển thị thông tin tòa nhà, diện tích thuê hoặc diện tích mong muốn, ngày bắt đầu, số tháng thuê, nhân viên phụ trách nếu đã được phân công và trạng thái xử lý.
4. A xem trạng thái hiện tại của từng yêu cầu.
4.1. Nếu A chưa có yêu cầu thuê nào, hệ thống hiển thị danh sách rỗng hoặc thông báo chưa có yêu cầu thuê.

## Bổ sung cho Quản lý

### 3.13 Scenario quản lý xem và phân công yêu cầu thuê

1. Manager A đăng nhập thành công và chọn chức năng "Yêu cầu thuê" từ menu quản trị.
2. Hệ thống hiển thị danh sách toàn bộ yêu cầu thuê của khách hàng, gồm thông tin khách hàng, tòa nhà, diện tích, ngày bắt đầu, số tháng thuê, ghi chú, nhân viên phụ trách và trạng thái.
3. A chọn một yêu cầu đang ở trạng thái pending.
4. Hệ thống hiển thị danh sách nhân viên để phân công xử lý yêu cầu.
5. A chọn một nhân viên và nhấn "Phân công".
6. Hệ thống kiểm tra nhân viên được chọn có vai trò staff.
6.1. Nếu người được chọn không phải nhân viên, hệ thống báo lỗi và không phân công.
7. Hệ thống gán yêu cầu thuê cho nhân viên đã chọn.
8. Nếu yêu cầu đang ở trạng thái pending, hệ thống chuyển trạng thái yêu cầu thành processing.
9. Hệ thống thông báo phân công yêu cầu thuê thành công và cập nhật lại danh sách.

### 3.14 Scenario quản lý xem danh sách hợp đồng thuê

1. Manager A đăng nhập thành công và chọn chức năng "Hợp đồng thuê".
2. Hệ thống hiển thị danh sách tất cả hợp đồng trong hệ thống.
3. Mỗi hợp đồng hiển thị mã hợp đồng, khách hàng, tòa nhà, diện tích thuê, nhân viên phụ trách, ngày bắt đầu, ngày kết thúc, tiền thuê tháng, tiền đặt cọc, chu kỳ thanh toán, ngày ký, trạng thái và ghi chú.
4. A xem thông tin hợp đồng để theo dõi các hợp đồng đã được tạo từ yêu cầu thuê.
4.1. Nếu chưa có hợp đồng nào, hệ thống hiển thị danh sách rỗng.

### 3.15 Scenario quản lý xem danh sách thanh toán

1. Manager A đăng nhập thành công và chọn chức năng "Thanh toán".
2. Hệ thống hiển thị danh sách tất cả khoản thanh toán phát sinh từ các hợp đồng thuê.
3. Mỗi khoản thanh toán hiển thị mã hợp đồng, khách hàng, loại thanh toán, số tiền, hạn thanh toán, ngày đã thanh toán, phương thức, trạng thái và ghi chú.
4. A xem trạng thái thanh toán để theo dõi các khoản unpaid và paid.
5. Hệ thống cho phép A theo dõi tổng quan tình hình thanh toán của toàn bộ hợp đồng trong hệ thống.

### 3.16 Scenario quản lý xem doanh thu theo nhân viên

1. Manager A đăng nhập thành công và chọn chức năng "Doanh thu nhân viên".
2. Hệ thống tổng hợp các khoản thanh toán đã có trạng thái paid theo từng nhân viên phụ trách hợp đồng.
3. Hệ thống hiển thị danh sách nhân viên kèm tổng số tiền đã thu được.
4. A xem bảng doanh thu để đánh giá kết quả xử lý hợp đồng và thanh toán của từng nhân viên.
4.1. Nếu nhân viên chưa có khoản thanh toán paid nào, hệ thống hiển thị doanh thu bằng 0.

## Bổ sung cho Nhân viên

### 4.5 Scenario nhân viên xem yêu cầu thuê được phân công

1. Nhân viên A đăng nhập thành công và chọn chức năng "Yêu cầu thuê".
2. Hệ thống hiển thị danh sách các yêu cầu thuê đã được manager phân công cho A.
3. Mỗi yêu cầu hiển thị thông tin khách hàng, tòa nhà, diện tích, ngày bắt đầu, số tháng thuê, ghi chú và trạng thái.
4. A xem danh sách yêu cầu cần xử lý.
4.1. Nếu A chưa được phân công yêu cầu thuê nào, hệ thống hiển thị danh sách rỗng.

### 4.6 Scenario nhân viên cập nhật trạng thái yêu cầu thuê

1. Nhân viên A đang ở màn hình danh sách yêu cầu thuê được phân công.
2. A chọn một yêu cầu cần xử lý.
3. A cập nhật trạng thái yêu cầu thành approved hoặc rejected.
4. Hệ thống kiểm tra yêu cầu đó có được phân công cho A hay không.
4.1. Nếu yêu cầu không thuộc phạm vi phân công của A, hệ thống báo lỗi và không cho cập nhật.
4.2. Nếu trạng thái không hợp lệ, hệ thống báo lỗi.
5. Hệ thống lưu trạng thái mới của yêu cầu thuê.
6. Hệ thống thông báo cập nhật trạng thái thành công.

### 4.7 Scenario nhân viên tạo hợp đồng từ yêu cầu thuê đã duyệt

1. Nhân viên A mở danh sách yêu cầu thuê được phân công.
2. A chọn một yêu cầu có trạng thái approved và nhấn chức năng tạo hợp đồng.
3. Hệ thống mở form tạo hợp đồng, gồm mã hợp đồng, ngày bắt đầu, ngày kết thúc, tiền thuê tháng, tiền đặt cọc, chu kỳ thanh toán, ngày ký và ghi chú.
4. A nhập đầy đủ thông tin hợp đồng và nhấn "Tạo hợp đồng".
5. Hệ thống kiểm tra dữ liệu hợp đồng.
5.1. Nếu yêu cầu chưa được duyệt, hệ thống báo lỗi không thể tạo hợp đồng.
5.2. Nếu yêu cầu không được phân công cho A, hệ thống báo lỗi.
5.3. Nếu mã hợp đồng trống, ngày kết thúc không sau ngày bắt đầu hoặc tiền thuê tháng không hợp lệ, hệ thống báo lỗi.
5.4. Nếu yêu cầu đã tạo hợp đồng trước đó, hệ thống báo lỗi.
5.5. Nếu diện tích thuê không còn trạng thái available, hệ thống báo lỗi.
6. Hệ thống tạo hợp đồng với trạng thái active.
7. Hệ thống tự động tạo các khoản thanh toán ban đầu gồm tiền đặt cọc nếu có và tiền thuê kỳ đầu tiên.
8. Hệ thống chuyển trạng thái yêu cầu thuê thành converted.
9. Hệ thống chuyển trạng thái diện tích thuê thành rented.
10. Hệ thống thông báo tạo hợp đồng thành công.

### 4.8 Scenario nhân viên xem danh sách hợp đồng đã tạo

1. Nhân viên A đăng nhập thành công và chọn chức năng "Hợp đồng".
2. Hệ thống hiển thị danh sách các hợp đồng do A phụ trách.
3. Mỗi hợp đồng hiển thị mã hợp đồng, khách hàng, tòa nhà, diện tích thuê, ngày bắt đầu, ngày kết thúc, tiền thuê tháng, tiền đặt cọc, chu kỳ thanh toán, trạng thái và ghi chú.
4. A xem danh sách hợp đồng để theo dõi các yêu cầu thuê đã được chuyển thành hợp đồng.
4.1. Nếu A chưa phụ trách hợp đồng nào, hệ thống hiển thị danh sách rỗng.

### 4.9 Scenario nhân viên xem và xác nhận thanh toán

1. Nhân viên A đăng nhập thành công và chọn chức năng "Thanh toán".
2. Hệ thống hiển thị danh sách các khoản thanh toán thuộc hợp đồng do A phụ trách.
3. A chọn một khoản thanh toán chưa thanh toán.
4. A nhập ngày thanh toán, phương thức thanh toán và ghi chú.
5. A nhấn "Xác nhận thanh toán".
6. Hệ thống kiểm tra khoản thanh toán có thuộc hợp đồng do A phụ trách hay không.
6.1. Nếu khoản thanh toán không thuộc phạm vi quản lý của A, hệ thống báo lỗi.
7. Hệ thống cập nhật ngày thanh toán, phương thức, ghi chú và chuyển trạng thái khoản thanh toán thành paid.
8. Hệ thống thông báo cập nhật thanh toán thành công.
