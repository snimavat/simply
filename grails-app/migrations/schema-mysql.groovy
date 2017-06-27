databaseChangeLog = {

    changeSet(author: "sudhir (generated)", id: "1498496799144-1") {
        createTable(tableName: "blog_index_page") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "intro", type: "CLOB")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-2") {
        createTable(tableName: "blog_page") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "body", type: "CLOB")

            column(name: "category", type: "BIGINT")

            column(name: "intro", type: "CLOB")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-3") {
        createTable(tableName: "flexi_page") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "body", type: "CLOB")

            column(name: "intro", type: "CLOB")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-4") {
        createTable(tableName: "home_page") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-5") {
        createTable(tableName: "page") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "pagePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "child_count", type: "INT")

            column(name: "date_created", type: "datetime")

            column(name: "keywords", type: "VARCHAR(255)")

            column(name: "last_updated", type: "datetime")

            column(name: "level", type: "INT")

            column(name: "meta_description", type: "TEXT")

            column(name: "parent_id", type: "BIGINT")

            column(name: "published", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "slug", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tree_path", type: "VARCHAR(255)")

            column(name: "url_path", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-6") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-7") {
        createTable(tableName: "site") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sitePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "hostname", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "is_default", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "root_page_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-8") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-9") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-10") {
        addPrimaryKey(columnNames: "id", constraintName: "blog_index_pagePK", tableName: "blog_index_page")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-11") {
        addPrimaryKey(columnNames: "id", constraintName: "blog_pagePK", tableName: "blog_page")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-12") {
        addPrimaryKey(columnNames: "id", constraintName: "flexi_pagePK", tableName: "flexi_page")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-13") {
        addPrimaryKey(columnNames: "id", constraintName: "home_pagePK", tableName: "home_page")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-14") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-15") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-16") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-17") {
        createIndex(indexName: "idx_slug", tableName: "page") {
            column(name: "slug")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-18") {
        addForeignKeyConstraint(baseColumnNames: "root_page_id", baseTableName: "site", constraintName: "FK2dbqxc9bflmmthxpq2jgnhjmg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "page")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-19") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-20") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "sudhir (generated)", id: "1498496799144-21") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "page", constraintName: "FKm3her2s210fu9951dqn3lmvjn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "page")
    }
}
