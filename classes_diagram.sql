CREATE TABLE "teacher" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "students" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "courses" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "student_course" (
  "id" integer PRIMARY KEY,
  "student_id" integer,
  "course_id" integer
);

ALTER TABLE "courses" ADD FOREIGN KEY ("id") REFERENCES "teacher" ("id");

ALTER TABLE "student_course" ADD FOREIGN KEY ("student_id") REFERENCES "students" ("id");

ALTER TABLE "student_course" ADD FOREIGN KEY ("course_id") REFERENCES "courses" ("id");
