-- DELETE FROM `users` WHERE user_id = 6;
 DELETE FROM `sml_table` WHERE sml_id >= 16;
 DELETE FROM `birthday` WHERE bd_id >= 16;
-- DELETE FROM calendar WHERE user_id = 6;
 UPDATE `calendar` set `birthday_ids` = '[]' WHERE `rowNum` = 1261;