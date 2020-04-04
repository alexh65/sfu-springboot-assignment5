package ca.coursePlanner.wrappers;

public class ApiDepartmentWrapper {
    public long deptId;
    public String name;

    public static ApiDepartmentWrapper makeNewWrapper (long deptId, String name){
        ApiDepartmentWrapper result = new ApiDepartmentWrapper();
        result.deptId = deptId;
        result.name = name;
        return result;
    }

}
