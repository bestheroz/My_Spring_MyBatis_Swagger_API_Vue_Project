INSERT
INTO `admin`
(LOGIN_ID,
 PASSWORD,
 NAME,
 ROLE_ID,
 SIGN_IN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '개발자', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer1', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '개발자1', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer2', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '개발자2', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer3', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '개발자3', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('1', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '임시계정', 1, 0, TRUE, DATEADD(YEAR, 1, NOW()),
        1, NOW(), 1, NOW()),
       ('admin', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '관리자계정', 2, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('writer', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '쓰기권한계정', 3, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('viewer', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '보기권한계정', 4, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('excel', '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח', '엑셀권한계정', 5, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW());
