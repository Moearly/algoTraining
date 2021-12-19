class TopVotedCandidate {
    // 人获得的票数
    Map<Integer,Integer> cntMap = new HashMap<>();
    // 当前时间领先的人
    TreeMap<Integer,Integer> tm = new TreeMap<>();
    
    public TopVotedCandidate(int[] persons, int[] times) {
        int maxId = -1;
        for(int i = 0;i < persons.length;i++) {
            int cnt = cntMap.getOrDefault(persons[i],0) + 1;
            cntMap.put(persons[i],cnt);
            // 之前得票最多的人
            int maxCnt = cntMap.getOrDefault(maxId,0);
            if(cnt >= maxCnt) {
                maxId = persons[i];
            }
            // 维护当前时间得票最多的personId
            tm.put(times[i],maxId);
        }
    }
    
    public int q(int t) {
        return tm.floorEntry(t).getValue();
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */