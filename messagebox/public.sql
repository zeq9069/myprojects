/*
Navicat PGSQL Data Transfer

Source Server         : localhost_postgresql
Source Server Version : 90103
Source Host           : localhost:5432
Source Database       : messagebox
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90103
File Encoding         : 65001

Date: 2015-03-16 11:43:56
*/


-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 59
 CACHE 1;
SELECT setval('"public"."hibernate_sequence"', 59, true);

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS "public"."announcement";
CREATE TABLE "public"."announcement" (
"id" int4 NOT NULL,
"content" varchar(255) COLLATE "default" NOT NULL,
"date" timestamp(6) NOT NULL,
"online" varchar(32) COLLATE "default" NOT NULL,
"publisher" varchar(64) COLLATE "default" NOT NULL,
"title" varchar(255) COLLATE "default" NOT NULL,
"type" varchar(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for group_announ
-- ----------------------------
DROP TABLE IF EXISTS "public"."group_announ";
CREATE TABLE "public"."group_announ" (
"ga_id" int4 NOT NULL,
"announ_id" int4,
"group_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."group_info";
CREATE TABLE "public"."group_info" (
"group_id" int4 NOT NULL,
"group_name" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS "public"."record";
CREATE TABLE "public"."record" (
"id" int4 NOT NULL,
"announ_id" int4,
"u_id" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."relation";
CREATE TABLE "public"."relation" (
"relation_id" int4 NOT NULL,
"group_id" int4,
"user_id" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
"user_id" varchar(32) COLLATE "default" NOT NULL,
"area_code" varchar(2) COLLATE "default",
"create_date" timestamp(6) NOT NULL,
"department" varchar(24) COLLATE "default",
"email" varchar(32) COLLATE "default",
"fax" varchar(32) COLLATE "default",
"fxmc" varchar(24) COLLATE "default" NOT NULL,
"job_title" varchar(24) COLLATE "default",
"login_date" timestamp(6),
"mobile_phone" varchar(32) COLLATE "default",
"office_phone" varchar(32) COLLATE "default",
"org_code" varchar(8) COLLATE "default" NOT NULL,
"org_name" varchar(24) COLLATE "default" NOT NULL,
"qq" varchar(16) COLLATE "default",
"update_date" timestamp(6),
"user_type" varchar(255) COLLATE "default",
"username" varchar(24) COLLATE "default" NOT NULL,
"real_name" varchar(16) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table announcement
-- ----------------------------
ALTER TABLE "public"."announcement" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table group_announ
-- ----------------------------
ALTER TABLE "public"."group_announ" ADD PRIMARY KEY ("ga_id");

-- ----------------------------
-- Primary Key structure for table group_info
-- ----------------------------
ALTER TABLE "public"."group_info" ADD PRIMARY KEY ("group_id");

-- ----------------------------
-- Primary Key structure for table record
-- ----------------------------
ALTER TABLE "public"."record" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table relation
-- ----------------------------
ALTER TABLE "public"."relation" ADD PRIMARY KEY ("relation_id");

-- ----------------------------
-- Indexes structure for table user_info
-- ----------------------------
CREATE UNIQUE INDEX "idx_usrinfo_uname" ON "public"."user_info" USING btree (username);

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD PRIMARY KEY ("user_id");

-- ----------------------------
-- Foreign Key structure for table "public"."group_announ"
-- ----------------------------
ALTER TABLE "public"."group_announ" ADD FOREIGN KEY ("group_id") REFERENCES "public"."group_info" ("group_id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."group_announ" ADD FOREIGN KEY ("announ_id") REFERENCES "public"."announcement" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."record"
-- ----------------------------
ALTER TABLE "public"."record" ADD FOREIGN KEY ("u_id") REFERENCES "public"."user_info" ("user_id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."record" ADD FOREIGN KEY ("announ_id") REFERENCES "public"."announcement" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."relation"
-- ----------------------------
ALTER TABLE "public"."relation" ADD FOREIGN KEY ("group_id") REFERENCES "public"."group_info" ("group_id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."relation" ADD FOREIGN KEY ("user_id") REFERENCES "public"."user_info" ("user_id") ON DELETE CASCADE ON UPDATE CASCADE;
