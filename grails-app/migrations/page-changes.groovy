databaseChangeLog = {
    String clid = "page-changes"

    changeSet(author: "sudhir (generated)", id: "${clid}.1") {
        addColumn(tableName: "page") {
            column(name: "unique_id", type: "varchar(255)")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-1") {
        addColumn(tableName: "page") {
            column(name: "shortid", type: "varchar(255)")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-2") {
        addUniqueConstraint(columnNames: "shortid", constraintName: "UC_PAGESHORTID_COL", tableName: "page")
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-3") {
        createIndex(indexName: "idx_page_treepath", tableName: "page") {
            column(name: "tree_path")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-4") {
        dropColumn(columnName: "unique_id", tableName: "page")
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-5") {
        createIndex(indexName: "idx_page_parent", tableName: "page") {
            column(name: "parent_id")
        }
    }

    changeSet(author: "sudhir (generated)", id: "1498732957893-6.1") {
        dropIndex(indexName: "idx_slug", tableName: "page")
        createIndex(indexName: "idx_page_slug", tableName: "page") {
            column(name: "slug")
        }
    }
}
