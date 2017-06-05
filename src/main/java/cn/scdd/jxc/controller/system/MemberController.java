package cn.scdd.jxc.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.entity.ScddMember;
import cn.scdd.jxc.service.member.MemberService;
import cn.scdd.jxc.util.Context.MemberLevelEnum;
import cn.scdd.jxc.util.KeyLabelVo;
import cn.scdd.jxc.util.MessageContext;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/sys/member")
public class MemberController extends BaseController {
	@Autowired
	private MemberService memberService;
	/**��Ա�ȼ��б�*/
	private Map<String, String> memberLevel = MemberLevelEnum.getKeyValues();
	
	/**
	 * ��Ա����ҳ��
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/member/add");
		modelAndView.addObject("levels", memberLevel);
		return modelAndView;
	}
	
	/**
	 * ��Ա�޸�ҳ��
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ScddMember member = this.memberService.searchMemberById(id);
		return this.initEditPage(member, false);
	}
	
	/**
	 * 初始化新增/编辑页面
	 * @param modelAndView
	 * @param hasErr
	 */
	private ModelAndView initEditPage(ScddMember member, boolean hasErr) {
		ModelAndView modelAndView = new ModelAndView("sys/member/add");
		modelAndView.addObject("member", member);
		modelAndView.addObject("levels", memberLevel);
		if(hasErr) {
			modelAndView.addObject(ERR_MSG, MessageContext.MEMBER_ERR_MSG_EXIST);
		}
		return modelAndView;
	}
	
	/**
	 * ��Ա����
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddMember member) {
		ModelAndView modelAndView = null;
		if(this.memberService.checkMemberExists(member)) {//��Ա�Ѿ�����
			modelAndView = this.initEditPage(member, true);
		} else {
			member.setModifierId(this.getLoginUserId());
			memberService.saveMember(member);
			modelAndView = new ModelAndView("sys/member/list");
		}
		
		return modelAndView;
	}
	
	/**
	 * 会员值返回
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/find")
	public List<KeyLabelVo> find(String term) {
		System.out.println("搜索关键字:" + term);
		List<KeyLabelVo> reList = null;
		List<ScddMember> list = this.memberService.searchAll();
		if(list != null && list.size() > 0) {
			reList = new ArrayList<KeyLabelVo>();
			for(ScddMember m : list) {
				if(m.getPhone().indexOf(term) >= 0 || 
						m.getName().indexOf(term) >= 0) {
					KeyLabelVo kVo = new KeyLabelVo();
					kVo.setKey(m.getId().toString());
					kVo.setLabel(m.getName() + "-" + m.getPhone());
					reList.add(kVo);
				}
			}
		}
		return reList;
	}
	
	/**
	 * ��Ա�б�
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddMember member) {
		ModelAndView modelAndView = new ModelAndView("sys/member/list");
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * ��Ա��ѯ
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddMember> search(ScddMember member) {
		PageVo<ScddMember> pageVo = new PageVo<ScddMember>();
		this.initPage();
		List<ScddMember> list = this.memberService.searchByMember(member);
		if(list != null) {
			Page<ScddMember> page = (Page<ScddMember>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("sys/member/records.ftl", contents));
		}
		return pageVo;
	}
}
