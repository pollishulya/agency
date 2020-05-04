package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.CommentProgram;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto extends BaseDto {
    private Long id;
    private String name;
    private String duration;
    private String type;
    private String price;
    private float rating;
  //  private Date exitDate;
    private String image;
    private Set<CommentProgram> comment = new HashSet<>();

  private String description;
    private Long companyId;
    private Set<BookingProgramDto> reservations = new HashSet<>();

}