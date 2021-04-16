package com.example.quest.dto;

import lombok.Data;

import java.util.List;

@Data
public class PollsResponse {

    private List<PollResponse> polls;

}
