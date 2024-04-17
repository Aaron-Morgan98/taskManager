package aaron.taskManager.database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private long task_id;
    private String task_name;
    private String task_desc;
    private String assigned_to;
    private String task_created;
    private String task_due;
    private String team;

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getTask_created() {
        return task_created;
    }

    public void setTask_created(String task_created) {
        this.task_created = task_created;
    }

    public String getTask_due() {
        return task_due;
    }

    public void setTask_due(String task_due) {
        this.task_due = task_due;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
