(ns webdev.items
  (:require [clojure.java.jdbc :as sql]))

(defn db-schema-migrated?
  []
  (-> (sql/query postgres)
    [(str "SELECT COUNT (*) from information_schema.tables" "where table_name='tasks'")]
    first :count pos?))

(defn apply-schema
  []
  (when (not (db-schema-migrated?))
    (sql/db-do-commands postgres
      (sql/create-table-ddl
        :tasks
        [:id :serial "PRIMARY KEY"]
        [:body :varchar "NOT NULL"]
        [:created_at :timestamp "NOT NULL" "DEFAULT_CURRRENT_TIMESTAMP"]))))

(defn create-task
  [task-name]
  (sql/insert! postgres
    :tasks [:body] task-name)
  (println task-name))

(defn all-tasks
  []
  (into [] (sql/query postgres "SELECT * FROM tasks order by id desc limit 128"))) 
      

    
