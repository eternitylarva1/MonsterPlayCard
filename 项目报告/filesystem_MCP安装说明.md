# Filesystem MCP 安装说明

## 概述

已成功安装filesystem MCP服务器，允许AI助手直接访问和操作项目文件系统。

## 配置详情

### 安装位置
- 配置文件：`.roo/mcp.json`
- 服务器名称：`filesystem`

### 配置参数
```json
{
  "mcpServers": {
    "filesystem": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "c:/Users/gaoming/Downloads/MonsterPlayCard"
      ]
    }
  }
}
```

### 访问路径
- 根目录：`c:/Users/gaoming/Downloads/MonsterPlayCard`
- 包含整个项目目录，包括源代码、配置文件和文档

## 功能特性

### 文件操作
- 读取文件内容
- 写入和修改文件
- 创建新文件和目录
- 删除文件和目录
- 移动和重命名文件

### 目录浏览
- 列出目录内容
- 递归浏览子目录
- 检查文件和目录属性

### 搜索功能
- 按文件名搜索
- 按文件内容搜索
- 支持正则表达式

## 使用场景

### 代码开发
- 直接编辑源代码文件
- 批量修改多个文件
- 代码重构和优化

### 文档管理
- 更新项目文档
- 整理文件结构
- 创建新的报告和分析文档

### 项目维护
- 清理临时文件
- 整理项目目录
- 备份重要文件

## 安全注意事项

### 访问限制
- MCP服务器只能访问指定的项目目录
- 无法访问系统文件或其他目录
- 所有操作都在项目范围内

### 操作建议
- 重要操作前先备份
- 批量修改时先测试
- 注意文件权限和依赖关系

## 故障排除

### 常见问题
1. **MCP服务器未启动**
   - 检查配置文件语法
   - 确认npx和npm已安装
   - 重启AI助手

2. **文件访问被拒绝**
   - 检查文件权限
   - 确认文件路径正确
   - 检查文件是否被其他程序占用

3. **操作失败**
   - 检查磁盘空间
   - 确认目录存在
   - 检查文件名是否合法

### 调试方法
- 查看MCP服务器日志
- 检查配置文件格式
- 测试简单文件操作

## 更新和维护

### 更新MCP服务器
```bash
npm update -g @modelcontextprotocol/server-filesystem
```

### 修改配置
如需更改访问路径或其他设置，编辑`.roo/mcp.json`文件：
```json
{
  "mcpServers": {
    "filesystem": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "新的路径"
      ]
    }
  }
}
```

---
*安装时间：2025-12-10*
*安装者：Roo (AI Assistant)*