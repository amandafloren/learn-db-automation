package page.repo;

public class DataRepo {
    public String queryGetDataNonF2F()throws Throwable {
        String Query = "SELECT " +
                "msp_sales_db.t_policy_urlcode_mapping_table.reference_number," +
                "msp_sales_db.t_policy_urlcode_mapping_table.url," +
                "to_char(to_timestamp(msp_sales_db.t_nonfacetoface_data.birth_date, 'YYYY-MM-DD'),'DDMMYYYY') as dob, " +
                "msp_sales_db.t_nonfacetoface_data.full_name,"+
                "msp_sales_db.t_nonfacetoface_data.email," +
                "to_char(msp_sales_db.t_nonfacetoface_data.created_date, 'DD/MM/YYYY') as created_date " +
                "FROM msp_sales_db.t_policy_urlcode_mapping_table " +
                "INNER JOIN msp_sales_db.t_nonfacetoface_data " +
                "ON msp_sales_db.t_policy_urlcode_mapping_table.reference_number = msp_sales_db.t_nonfacetoface_data.reference_number " +
                "INNER JOIN msp_sales_db.t_cust_confirmation " +
                "ON msp_sales_db.t_policy_urlcode_mapping_table.cus_confirmation_id = msp_sales_db.t_cust_confirmation.id " +
                "WHERE msp_sales_db.t_policy_urlcode_mapping_table.source = 'NON-FACETOFACE' " +
                "AND msp_sales_db.t_nonfacetoface_data.created_by ='ASNTBX' " +
                "AND msp_sales_db.t_cust_confirmation.status='URL GENERATED' "+
                "order by msp_sales_db.t_policy_urlcode_mapping_table.created_date desc limit 1;";
       // System.out.println(Query);

        return Query;
    }

    public String queryGetASNTBXData() throws Throwable{
        String Query2 =  "SELECT " +
                "msp_sales_db.t_policy_urlcode_mapping_table.reference_number," +
                "msp_sales_db.t_policy_urlcode_mapping_table.url," +
                "to_char(to_timestamp(msp_sales_db.t_nonfacetoface_data.birth_date, 'YYYY-MM-DD'),'DDMMYYYY') as dob, " +
                "msp_sales_db.t_nonfacetoface_data.full_name,"+
                "msp_sales_db.t_nonfacetoface_data.email," +
                "to_char(msp_sales_db.t_nonfacetoface_data.created_date, 'DD/MM/YYYY') as created_date " +
                "FROM msp_sales_db.t_policy_urlcode_mapping_table " +
                "INNER JOIN msp_sales_db.t_nonfacetoface_data " +
                "ON msp_sales_db.t_policy_urlcode_mapping_table.reference_number = msp_sales_db.t_nonfacetoface_data.reference_number " +
                "INNER JOIN msp_sales_db.t_cust_confirmation " +
                "ON msp_sales_db.t_policy_urlcode_mapping_table.reference_number = msp_sales_db.t_cust_confirmation.reference_no " +
                "WHERE msp_sales_db.t_policy_urlcode_mapping_table.source = 'NON-FACETOFACE' " +
                "AND msp_sales_db.t_nonfacetoface_data.created_by ='ASNTBX' " +
                "AND msp_sales_db.t_cust_confirmation.status='URL GENERATED' "+
                "order by msp_sales_db.t_policy_urlcode_mapping_table.created_date desc limit 1;";

        return Query2;
    }

}
