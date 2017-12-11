package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
    private Path logDir;
    private List<EventRow> logData;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs(logDir);
    }

    private Set<String> getIPSet(String user, Event event, Status status, Date after, Date before) {
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .filter(s -> filter(s, null, user, event, status, -1))
                .filter(s -> dateBetween(s.date, after, before))
//                .peek(System.out::println)
                .map(s -> s.ip)
                .collect(Collectors.toSet());
    }

    private boolean dateBetween(Date date, Date after, Date before) {
        return (after == null || date.after(after) || date.equals(after)) &&
                (before == null || date.before(before) || date.equals(before));
    }

    private boolean filter(EventRow row, String ip, String user, Event event, Status status, int task       ) {
        if      (row    == null) return false;
        if      (ip     != null) return row.ip.equalsIgnoreCase(ip);
        else if (user   != null) return row.name.equalsIgnoreCase(user);
        else if (event  != null) return row.event == event;
        else if (status != null) return row.status == status;
        else if (task   != -1)   return row.task == task;

        return true;
    }

    private boolean filter(EventRow row, String user, Event event, Status status) {
        if (row == null) return false;
        if (user != null) return row.name.equalsIgnoreCase(user);
        else if (event != null) return row.event == event;
        else if (status != null) return row.status == status;

        return true;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getIPSet(null, null, null, after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getIPSet(null, null, null, after, before);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getIPSet(user, null, null, after, before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getIPSet(null, event, null, after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getIPSet(null, null, status, after, before);
    }

    private void readLogs(Path logDir) {
        if (this.logData == null) this.logData = new ArrayList<>();

        try {
            for (Path log : Files.newDirectoryStream(logDir)) {
                if (Files.isRegularFile(log) && log.toString().endsWith(".log")) {
                    logData.addAll(Files
                            .readAllLines(log)
                            .stream()
                            .map(s -> new EventRow(s))
                            .collect(Collectors.toList()));
                } else {
                    if (Files.isDirectory(log)) readLogs(log);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getAllUsers() {
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .map(s -> s.name)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .filter(s->dateBetween(s.date,after,before))
                .map(s -> s.name)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .filter(s->s.name.equalsIgnoreCase(user))
                .filter(s->dateBetween(s.date, after, before))
                .map(s -> s.event)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .filter(s->s.ip.equals(ip))
                .filter(s->dateBetween(s.date, after, before))
                .map(s -> s.name)
                .collect(Collectors.toSet());
    }

    private Set<String> getUsersByEvent(Event event, Date after, Date before, int task){
        if (logData == null) readLogs(logDir);

        return logData
                .stream()
                .filter(s->s.event.equals(event))
                .filter(s->dateBetween(s.date, after, before))
                .filter(s->{if(s.task==-1 || task ==-1) return true;
                            else return s.task == task;})
                .map(s -> s.name)
                .collect(Collectors.toSet());
    }
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersByEvent(Event.LOGIN, after, before, -1);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersByEvent(Event.DOWNLOAD_PLUGIN, after, before, -1);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersByEvent(Event.WRITE_MESSAGE, after, before, -1);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.SOLVE_TASK, after, before, -1);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(Event.SOLVE_TASK, after, before, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.DONE_TASK, after, before, -1);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(Event.DONE_TASK, after, before, task);
    }

    private Set<Date> getDates(String user, Event event, Status status, int task, Date after, Date before){
        if (logData == null) readLogs(logDir);
        return logData
                .stream()
                .filter(s->filter(s,null,user,event,status,task))
                .filter(s->filter(s,null,null,event,status,task))
                .filter(s->filter(s,null,null,null,status,task))
                .filter(s->filter(s,null,null,null,null,task))
                .filter(s->dateBetween(s.date,after,before))
                .map(s->s.date)
                .collect(Collectors.toSet());
    }
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getDates(user,event,null,-1,after,before);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDates(null,null,Status.FAILED,-1,after,before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDates(null,null,Status.ERROR,-1,after,before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<EventRow> preFiltered = logData
                .stream()
                .filter(s->s.name.equalsIgnoreCase(user))
                .filter(s->dateBetween(s.date, after, before))
                .sorted((s1,s2)->(s1.date.compareTo(s2.date)))
                .collect(Collectors.toList());
        Date date = null;
        for(EventRow row : preFiltered){
            if(row.event == Event.LOGIN){
                date = row.date;
                break;
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<EventRow> preFiltered = logData
                .stream()
                .filter(s->s.name.equalsIgnoreCase(user))
                .filter(s->s.task==task)
                .filter(s->dateBetween(s.date, after, before))
                .sorted((s1,s2)->(s1.date.compareTo(s2.date)))
                .collect(Collectors.toList());
        Date date = null;
        for(EventRow row : preFiltered){
            if(row.event == Event.SOLVE_TASK){
                date = row.date;
                break;
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<EventRow> preFiltered = logData
                .stream()
                .filter(s->s.name.equalsIgnoreCase(user))
                .filter(s->s.task==task)
                .filter(s->dateBetween(s.date, after, before))
                .sorted((s1,s2)->(s1.date.compareTo(s2.date)))
                .collect(Collectors.toList());
        Date date = null;
        for(EventRow row : preFiltered){
            if(row.event == Event.DONE_TASK){
                date = row.date;
                break;
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDates(user,Event.WRITE_MESSAGE,null,-1,after,before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDates(user,Event.DOWNLOAD_PLUGIN,null,-1,after,before);
    }



    private Set<Event> getEvents(String ip, String user, Event event, Status status, int task, Date after, Date before){
        if (logData == null) readLogs(logDir);
        return logData
                .stream()
                .filter(s->filter(s,ip,user,event,status,task))
                .filter(s->filter(s,null,user,event,status,task))
                .filter(s->filter(s,null,null,event,status,task))
                .filter(s->filter(s,null,null,null,status,task))
                .filter(s->filter(s,null,null,null,null,task))
                .filter(s->dateBetween(s.date,after,before))
                .map(s->s.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getEvents(null,null, null, null, -1, after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getEvents(null,null, null, null, -1, after, before);
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getEvents(ip,null, null, null, -1, after, before);
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getEvents(null,user, null, null, -1, after, before);
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEvents(null,null, null, Status.FAILED, -1, after, before);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEvents(null,null, null, Status.ERROR, -1, after, before);
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return logData
                .stream()
                .filter(s->dateBetween(s.date, after, before))
                .filter(s->s.task == task)
                .filter(s->s.event == Event.SOLVE_TASK)
                .collect(Collectors.toList()).size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return logData
                .stream()
                .filter(s->dateBetween(s.date, after, before))
                .filter(s->s.task == task)
                .filter(s->(s.event.equals(Event.SOLVE_TASK)))
                .filter(s->s.status == Status.OK)
                .collect(Collectors.toList()).size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {

        return null;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        List<EventRow> list = logData
                .stream()
                .filter(s->dateBetween(s.date, after, before))
                .filter(s->s.event.equals(Event.DONE_TASK))
                .collect(Collectors.toList());
        for(EventRow row : list){
            map.putIfAbsent(row.task, 1);
            map.computeIfPresent(row.task, (v1, v2)->(v1+1));
        }
        return null;
    }


    private class EventRow {
        private String ip;
        private String name;
        private Date date;
        private Event event;
        private int task;
        private Status status;

        public EventRow(String logRow) {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("dd.MM.yyyy HH:mm:ss");

            String[] s = logRow.split("\\t");
            ip = s[0];
            name = s[1];
            try {
                date = sdf.parse(s[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            event = getEvent(s[3]);
            task = getTask(s[3]);
            status = Status.valueOf(s[4].toUpperCase());
        }

        private Event getEvent(String s) {
            int i;
            String event = s.toUpperCase();
            if ((i = event.indexOf(" ")) > 0) event = event.substring(0, i);
            return Event.valueOf(event);
        }

        private int getTask(String s) {
            int i;
            String task = s.toUpperCase();
            if ((i = task.indexOf(" ")) > 0) return Integer.parseInt(task.substring(i).trim());
            return -1;
        }

        @Override
        public String toString() {
            return "EventRow{" +
                    "ip='" + ip + '\'' +
                    ", name='" + name + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", task=" + task +
                    ", status=" + status +
                    '}';
        }
    }
}