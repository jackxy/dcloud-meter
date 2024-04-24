package net.xdclass.service.common;

import net.xdclass.dto.common.SysDictDTO;

import java.util.List;
import java.util.Map;

public interface SysDictService {

    Map<String, List<SysDictDTO>> listByCategory(String[] category);
}
