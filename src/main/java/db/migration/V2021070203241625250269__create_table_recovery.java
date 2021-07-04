package db.migration;

import static org.jooq.impl.DSL.foreignKey;
import static org.jooq.impl.DSL.primaryKey;
import static org.jooq.impl.DSL.unique;
import static org.jooq.impl.DSL.using;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.TIMESTAMP;
import static org.jooq.impl.SQLDataType.VARCHAR;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V2021070203241625250269__create_table_recovery extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());        
        create.transaction(configuration -> {
            using(configuration)
                .createTableIfNotExists("recovery")
                    .column("id", BIGINT.identity(true))
                    .column("code", VARCHAR(4).nullable(false))
                    .column("expires_in", TIMESTAMP.nullable(false))
                    .column("user_id", BIGINT.nullable(false))
                .constraints(
                    primaryKey("id"),
                    unique("code"),
                    foreignKey("user_id").references("user", "id"))
                .execute();
        });
    }
}
